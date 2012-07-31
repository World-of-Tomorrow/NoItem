package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Log;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;
import net.worldoftomorrow.nala.ni.Items.Tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class NoUseListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Log.debug("Player Interact Event");
		Action action = event.getAction();
		if (action.equals(Action.LEFT_CLICK_BLOCK)) {
			this.handleBlockLeftClick(event);
		}
		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			this.handleBlockRightClick(event);
		}
		// I need to handle right and left click air
	}

	private void handleBlockRightClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		ItemStack stack = new ItemStack(p.getItemInHand());
		Block clicked = event.getClickedBlock();
		Material type = stack.getType();
		stack.setDurability((short) 0);
		if (type.equals(Material.FLINT_AND_STEEL)) {
			if (Perms.NOUSE.has(p, stack)) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.USE, stack.getTypeId());
				StringHelper.notifyAdmin(p, EventTypes.USE, stack);
			}
			return;
		}
		if (type.equals(Material.DIRT) || type.equals(Material.GRASS)) {
			// If it is a hoe (gotta handle them hoes!)
			if (stack.getTypeId() >= 290 && stack.getTypeId() <= 294) {
				if (Perms.NOUSE.has(p, stack)) {
					event.setCancelled(true);
					StringHelper.notifyPlayer(p, EventTypes.USE,
							stack.getTypeId());
					StringHelper.notifyAdmin(p, EventTypes.USE, stack);
				}
			}
			return;
		}
		if (clicked.getType().equals(Material.LEVER)
				|| clicked.getType().equals(Material.STONE_BUTTON)
				|| clicked.getType().equals(Material.WOOD_DOOR)
				|| clicked.getType().equals(Material.BREWING_STAND)
				|| clicked.getType().equals(Material.CHEST)
				|| clicked.getType().equals(Material.FURNACE)
				|| clicked.getType().equals(Material.DISPENSER)
				|| clicked.getType().equals(Material.WORKBENCH)
				|| clicked.getType().equals(Material.ENCHANTMENT_TABLE)
				/*|| CustomBlocks.isCustomBlock(clicked)*/) {
			if (Perms.NOUSE.has(p,
					new ItemStack(clicked.getTypeId(), clicked.getData()))) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.USE,
						clicked.getTypeId());
				StringHelper.notifyAdmin(p, EventTypes.USE, new ItemStack(
						clicked.getTypeId(), clicked.getData()));
			}
		}
	}

	private void handleBlockLeftClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block b = event.getClickedBlock();
		ItemStack stack = new ItemStack(p.getItemInHand());
		Material type = stack.getType();
		if (type.equals(Material.FLINT_AND_STEEL)
				&& b.getType().equals(Material.TNT)) {
			if (Perms.NOUSE.has(p, stack)) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.USE, stack.getTypeId());
				StringHelper.notifyAdmin(p, EventTypes.USE, stack);
			}
		}
	}

	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent event) {
		Player p = event.getPlayer();
		int bucketID = event.getBucket().getId();
		if (Perms.NOUSE.has(p, event.getItemStack())) {
			event.setCancelled(true);
			StringHelper.notifyPlayer(p, EventTypes.USE, bucketID);
			StringHelper.notifyAdmin(p, EventTypes.USE, event.getItemStack());
		}
	}

	@EventHandler
	public void onBucketFill(PlayerBucketFillEvent event) {
		Player p = event.getPlayer();
		int bucketID = event.getBucket().getId();
		if (Perms.NOUSE.has(p, event.getItemStack())) {
			event.setCancelled(true);
			StringHelper.notifyPlayer(p, EventTypes.USE, bucketID);
			StringHelper.notifyAdmin(p, EventTypes.USE, event.getItemStack());
		}
	}

	@EventHandler
	public void onPlayerShear(PlayerShearEntityEvent event) {
		Player p = event.getPlayer();
		ItemStack shears = new ItemStack(Material.SHEARS);
		shears.setDurability((short) 0);
		if (Perms.NOUSE.has(p, shears)) {
			event.setCancelled(true);
			StringHelper.notifyPlayer(p, EventTypes.USE, shears.getTypeId());
			StringHelper.notifyAdmin(p, EventTypes.USE, shears);
		}
	}

	@EventHandler
	public void onSwordSwing(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (damager instanceof Player) {
			Player p = (Player) damager;
			ItemStack stack = new ItemStack(p.getItemInHand());
			stack.setDurability((short) 0);
			Material type = stack.getType();
			if (Tools.isTool(type) && Perms.NOUSE.has(p, stack)) {
				event.setCancelled(true);
				StringHelper.notifyPlayer(p, EventTypes.USE, stack.getTypeId());
				StringHelper.notifyAdmin(p, EventTypes.USE, stack);
			}
		}
	}
	// TODO: Add bow support
}
