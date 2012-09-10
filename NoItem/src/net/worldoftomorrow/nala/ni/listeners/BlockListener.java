package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlock();
		if (Perms.NOBREAK.has(p, b)) {
			event.setCancelled(true);
			this.notify(p, EventTypes.BREAK, new ItemStack(b.getType()));
			return;
		}

		ItemStack inhand = p.getItemInHand();
		if (inhand != null && Perms.NOUSE.has(p, inhand)) {
			event.setCancelled(true);
			this.notify(p, EventTypes.USE, new ItemStack(inhand.getType()));
			return;
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlock();
		if (Perms.NOPLACE.has(p, b)) {
			event.setCancelled(true);
			this.notify(p, EventTypes.PLACE, new ItemStack(b.getType()));
			return;
		}

		ItemStack inhand = event.getItemInHand();
		if (inhand != null && Perms.NOUSE.has(p, inhand)) {
			event.setCancelled(true);
			this.notify(p, EventTypes.USE, inhand);
			return;
		}
	}

	private void notify(Player p, EventTypes type, ItemStack stack) {
		StringHelper.notifyPlayer(p, type, stack);
		StringHelper.notifyAdmin(p, type, stack);
	}
}
