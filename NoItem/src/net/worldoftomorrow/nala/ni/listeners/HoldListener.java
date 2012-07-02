package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Log;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HoldListener implements Listener {

	@EventHandler
	public void onItemSwitch(PlayerItemHeldEvent event) {
		Log.debug("PlayerItemHeldEvent fired");

		Player p = event.getPlayer();
		int ns = event.getNewSlot();
		int ps = event.getPreviousSlot();
		int iid = 0;
		ItemStack notAllowed = null;
		ItemStack allowed = null;

		if (p.getInventory().getItem(ns) != null) {
			iid = p.getInventory().getItem(ns).getTypeId();
			notAllowed = p.getInventory().getItem(ns);
			allowed = p.getInventory().getItem(ps);

			// Switch the items.
			if (Perms.NOHOLD.has(p, notAllowed)) {
				p.getInventory().setItem(ns, allowed);
				p.getInventory().setItem(ps, notAllowed);
				StringHelper.notifyPlayer(p, EventTypes.HOLD, iid);
				StringHelper.notifyAdmin(p, EventTypes.HOLD, notAllowed);
			}
		}
	}

	@EventHandler
	public void onItemPickUp(PlayerPickupItemEvent event) {
		Player p = event.getPlayer();
		ItemStack stack = event.getItem().getItemStack();
		PlayerInventory inv = p.getInventory();
		if (Perms.NOHOLD.has(p, stack)) {
			if (inv.getItemInHand().getTypeId() == 0
					&& inv.firstEmpty() == inv.getHeldItemSlot()) {
				event.setCancelled(true);
				event.getItem().setPickupDelay(200);
				StringHelper
						.notifyPlayer(p, EventTypes.HOLD, stack.getTypeId());
				StringHelper.notifyAdmin(p, EventTypes.HOLD, stack);
			}
		}
	}
}
