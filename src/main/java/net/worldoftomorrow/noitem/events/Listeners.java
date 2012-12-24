package net.worldoftomorrow.noitem.events;

import net.worldoftomorrow.noitem.NoItem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Listeners implements Listener {
	
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		Handlers.handleItemPickup(event);
		Handlers.handleNoHavePickup(event);
		Handlers.handleNoHoldPickup(event);
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Handlers.handleItemDrop(event);
	}
	
	@EventHandler
	public void onItemHeld(PlayerItemHeldEvent event) {
		Handlers.handleItemHeld(event);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Handlers.handleInteract(event);
		Handlers.handlerInteractLR(event);
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Handlers.handleInteractEntity(event);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Handlers.handleBlockBreak(event);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Handlers.handleBlockPlace(event);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Handlers.handleNoHoldInvClick(event);
		Handlers.handleNoBrewInvClick(event);
		Handlers.handleNoWearInvClick(event);
		Handlers.handleNoCookInvClick(event);
	}
	
	@EventHandler
	public void onItemCraft(CraftItemEvent event) {
		Handlers.handleItemCraft(event);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Handlers.handlePlayerDeath(event);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Handlers.handlePlayerSpawn(event);
	}
}
