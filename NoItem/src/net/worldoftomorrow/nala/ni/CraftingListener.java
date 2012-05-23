package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftingListener implements Listener {
	
	private static Log log = new Log();

	private List<String> rawItems = Configuration.disallowedCrafting();

	private List<String> dItems = new ArrayList<String>();

	private boolean itemsAdded = false;

	private void addItems() {
		for (String raw : rawItems) {
			if (raw.contains(":")) {
				dItems.add(raw);
			} else {
				String n = raw.concat(":0");
				dItems.add(n);
			}
		}
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent event) {
		if (!itemsAdded) {
			addItems();
			itemsAdded = true;
		}

		log.debug("CraftItemEvent Fired");

		Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
		int iid = event.getCurrentItem().getTypeId();
		int dv = event.getCurrentItem().getDurability();
		
		//If the item list should be used//
		if (!Configuration.perItemPerms()) {
			if (dItems.contains(iid + ":" + dv) && !Perms.ALLITEMS.has(p)) {
				
				event.setCancelled(true);
				
				if (Configuration.notifyNoCraft()) {
					StringHelper.notifyPlayer(p,
							Configuration.noCraftMessage(), iid);
				}
				if (Configuration.notifyAdmins()) {
					StringHelper.notifyAdmin(p, EventTypes.CRAFT, event.getCurrentItem());
				}
			} else {
				log.debug("This item can be crafted");
			}
		//Else use permissions//
		} else {
			if(Perms.NOCRAFT.has(p, iid, dv)){
				
				event.setCancelled(true);
				
				if(Configuration.notifyNoCraft()){
					StringHelper.notifyPlayer(p, Configuration.noCraftMessage(), iid);
				}
				if (Configuration.notifyAdmins()) {
					StringHelper.notifyAdmin(p, EventTypes.CRAFT, event.getCurrentItem());
				}
			} else {
				log.debug("This item can be crafted");
			}
		}
	}
}