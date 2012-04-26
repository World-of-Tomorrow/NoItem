package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.worldoftomorrow.nala.ni.Config.ConfigFile;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public class BrewingListener implements Listener {

	private static Log log = new Log();
	private ConfigFile conf = Config.ConfigFile.CONFIG;

	private boolean perItemPerms = Config.getConfig(conf).getBoolean(
			"PerItemPermissions");
	private List<String> recipes = Config.getConfig(conf).getStringList(
			"DisallowedPotionRecipes");
	private List<String> dPotions = new ArrayList<String>();
	
	private boolean checked = false;
	private boolean debug = Config.getConfig(conf).getBoolean("Debugging");

	// This method is just to check the configuration
	private void checkRecipes() {
		for (String raw : recipes) {
			if (!raw.contains(":")) {
				log.log(Level.WARNING,
						"DisallowedPotionRecipes configuration error: \n Recipe does not contain ingredient or potion. ( "
								+ raw
								+ " ) \n"
								+ "Potions will not be blocked until this is fixed!");
				return;
			}
			dPotions.add(raw);
		}
		checked = true;
	}

	private int[] ItemStackDV(BrewerInventory inv) {
		
		List<Integer> notEmpty = new ArrayList<Integer>();
		List<Integer> durability = new ArrayList<Integer>();
		int[] dvs;
		int iterationCount = 0;
		
		ItemStack[] contents = inv.getContents();
		
		//First, find the spaces that are not empty
		for(ItemStack i : contents){
			//If the stack is null and it is not the ingredient (which is space 3)
			if(i != null && iterationCount < 3){
				notEmpty.add(iterationCount);
			}
			iterationCount++;
		}
		for(Integer i : notEmpty){
			durability.add((int) inv.getItem(i).getDurability());
		}
		dvs = new int[notEmpty.size()];
		iterationCount = 0;
		for(Integer i : durability){
			dvs[iterationCount] = i;
			iterationCount++;
		}
		
		return dvs;
	}

	@EventHandler
	public void onPotionBrew(BrewEvent event) {
		if(debug){
			log.log("Potion brew event fired.");
		}
		if (!checked) {
			checkRecipes();
		}
		
		int ingredient = event.getContents().getIngredient().getTypeId();
		int[] dvs = ItemStackDV(event.getContents());
		
		// Really complicated, has to be an easier way.
		Player p = Bukkit.getServer().getPlayer(
				event.getContents().getViewers().get(0).getName());
		
		if (!perItemPerms) {
			for (int i : dvs) {
				// This check currently is not useful because it compares the
				// ingredient ID to Item DV. But it should still work.
				if (i != ingredient) {
					if (dPotions.contains(i + ":" + ingredient)) {
						event.setCancelled(true);
					}
				}
			}
		} else {
			for (int i : dvs) {
				// This check currently is not useful because it compares the
				// ingredient ID to Item DV. But it should still work.
				if (i != ingredient) {
					if (Permissions.NOBREW.has(p, i, ingredient, true) && !Permissions.ALLITEMS.has(p)) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
