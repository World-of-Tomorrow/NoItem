package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class FurnaceListener implements Listener {
    @EventHandler
    public void onFurnaceCook(InventoryClickEvent event) {
	Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
	if (event.getInventory().getType().equals(InventoryType.FURNACE)) {
	    if (event.getRawSlot() == 0 && event.getCursor() != null) {
		ItemStack stack = event.getCursor();
		int id = stack.getTypeId();
		if (Perms.NOCOOK.has(p, stack)) {
		    event.setCancelled(true);
		    if (Cookable.getItem(id).isFood()) {
			StringHelper.notifyPlayer(p, EventTypes.COOK, id);
			StringHelper.notifyAdmin(p, EventTypes.COOK, stack);
		    } else {
			StringHelper.notifyPlayer(p, EventTypes.SMELT, id);
			StringHelper.notifyAdmin(p, EventTypes.SMELT, stack);
		    }

		}
	    }
	}
    }
}
