package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Log;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NewNoUseListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Log.debug("Player Interact Event");
		if (event.isBlockInHand())
			return; // Return if it is a block place event
		Action action = event.getAction();
		if (action.equals(Action.LEFT_CLICK_BLOCK)) {
			this.handleBlockLeftClick(event);
		}
		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			this.handleBlockRightClick(event);
		}
	}

	private void handleBlockLeftClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block clicked = event.getClickedBlock();
		ItemStack inHand = event.getItem();
		switch (inHand.getType()) {
		case FLINT_AND_STEEL:
			event.setCancelled(this.handleLighter(p, clicked, inHand));
			break;
		case DIRT:
		case GRASS:
			event.setCancelled(this.handleHoe(p, inHand));
			break;
		default:
			break;
		}
	}

	private void handleBlockRightClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block clicked = event.getClickedBlock();
		ItemStack inHand = event.getItem();

	}

	private boolean handleLighter(Player p, Block clicked, ItemStack inHand) {
		return false;
	}

	private boolean handleHoe(Player p, ItemStack inHand) {
		int id = inHand.getTypeId();
		if (id >= 290 && id <= 294) {
			if (Perms.NOUSE.has(p, inHand)) {
				StringHelper.notifyPlayer(p, EventTypes.USE, id);
				StringHelper.notifyAdmin(p, EventTypes.USE, inHand);
				return true;
			}
		}
		return false;
	}
}
