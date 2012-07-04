package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Log;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropListener implements Listener {
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Log.debug("ItemDropEvent fired.");
		Player p = event.getPlayer();
		ItemStack stack = event.getItemDrop().getItemStack();
		int iid = stack.getTypeId();
		if (Perms.NODROP.has(p, stack)) {
			Log.debug("Player has the permission node");
			event.setCancelled(true);
			StringHelper.notifyPlayer(p, EventTypes.DROP, iid);
			StringHelper.notifyAdmin(p, EventTypes.DROP, stack);
		} else {
			Log.debug("Player does not have the permission node");
		}
	}
}
