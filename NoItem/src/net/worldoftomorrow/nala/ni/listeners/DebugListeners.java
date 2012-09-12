package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.Log;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DebugListeners implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInvClick(InventoryClickEvent event) {
		int clicked = event.getRawSlot();
		String item = event.getCurrentItem() == null ? "empty" : ""
				+ event.getView().getItem(clicked).getType().name();
		Log.debug("Inventory Click Event: ");
		Log.debug("    Item: " + item);
		Log.debug("    Raw Slot: " + event.getRawSlot());
		Log.debug("    View: " + event.getView().getType());
		Log.debug("    Type: " + event.getInventory().getType());
		Log.debug("    Slot: " + event.getSlotType());
		Log.debug("    Cancelled: " + event.isCancelled());
	}

	//@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event) {
		Log.debug("Block Place Event: ");
		Log.debug("    ID: " + event.getBlock().getTypeId());
		Log.debug("    DV: " + event.getBlock().getData());
		Log.debug("    Cancelled: " + event.isCancelled());
	}
}
