package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {

	private static List<String> dItems = new ArrayList<String>();

	protected static void addItems() {
		dItems.clear();
		for (String raw : Config.disallowedCrafting()) {
			if (raw.contains(":")) {
				dItems.add(raw);
			} else {
				String n = raw.concat(":0");
				dItems.add(n);
			}
		}
	}

	public CraftingListener() {
		addItems();
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent event) {
		Log.debug("CraftItemEvent Fired");

		Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
		ItemStack stack = event.getInventory().getResult();
		int iid = stack.getTypeId();
		int dv = stack.getDurability();

		// If the item list should be used//
		if (!Config.perItemPerms()) {
			if (dItems.contains(iid + ":" + dv) && !Perms.ALLITEMS.has(p)) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.CRAFT, iid);
				StringHelper.notifyAdmin(p, EventTypes.CRAFT, stack);
			} else {
				Log.debug("This item can be crafted");
			}
			// Else use permissions//
		} else {
			if (Perms.NOCRAFT.has(p, stack)) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.CRAFT, iid);
				StringHelper.notifyAdmin(p, EventTypes.CRAFT, stack);
			} else {
				Log.debug("This item can be crafted");
			}
		}
	}
}