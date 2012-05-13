package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BrewingListener implements Listener {

	private static Log log = new Log();

	private boolean perItemPerms = Configuration.perItemPerms();

	private boolean debug = Configuration.debugging();
	
	private List<String> recipes = Configuration.disallowedPotions();
	
	private List<String> dPotions = new ArrayList<String>();
	
	private boolean checked = false;

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

	private int[] ItemStackDV(Inventory inv) {
		
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
	public void onBrewerInvEvent(InventoryClickEvent event){
		if(debug){
			log.log("InventoryClick event fired. " + event.getRawSlot() + " " + event.getInventory().getType().name());
		}
		
		if(event.getInventory().getType().equals(InventoryType.BREWING)){
			if (!checked) {
				checkRecipes();
			}
			
			//Get the player this way now.
			Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
			int slot = event.getRawSlot();
			
			//If it is not the ingredient slot
			if (slot != 3) {
				if (event.getInventory().getItem(3) != null) {
					int dv = p.getItemOnCursor().getDurability();
					int ingredient = event.getInventory().getItem(3).getTypeId();
					if(event.getRawSlot() < 3){
						if (!perItemPerms) {
							if (dPotions.contains(dv + ":" + ingredient)) {
								event.setCancelled(true);
								if (Configuration.notifyNoBrew()) {
									p.sendMessage(ChatColor.BLUE + Configuration.noBrewMessage());
								}
							}
						} else {
							if (Permissions.NOBREW.has(p, dv, ingredient) || !p.isOp()) {
								event.setCancelled(true);
								if (Configuration.notifyNoBrew()) {
									p.sendMessage(ChatColor.BLUE + Configuration.noBrewMessage());
								}
							}
						}	
					}
				}
			//INGREDIENT SLOT CLICKED
			} else {
				int ingredient = p.getItemOnCursor().getTypeId();
				int[] dvs = this.ItemStackDV(event.getInventory());
				if(!perItemPerms){
					for(Integer dv : dvs){
						if(dPotions.contains(dv + ":" + ingredient)){
							event.setCancelled(true);
							if (Configuration.notifyNoBrew()) {
								p.sendMessage(ChatColor.BLUE + Configuration.noBrewMessage());
							}
						}	
					}
				} else {
					for (Integer dv : dvs) {
						if (Permissions.NOBREW.has(p, dv, ingredient) || !p.isOp()) {
							event.setCancelled(true);
							if (Configuration.notifyNoBrew()) {
								p.sendMessage(ChatColor.BLUE + Configuration.noBrewMessage());
							}
							break;
						}
					}
				}
			}
		}
	}
}
