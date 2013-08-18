package net.worldoftomorrow.noitem.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class Listeners implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemPickup(PlayerPickupItemEvent event) {
		Handlers.handleItemPickup(event);
		Handlers.handleNoHavePickup(event);
		Handlers.handleNoHoldPickup(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemDrop(PlayerDropItemEvent event) {
		Handlers.handleItemDrop(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemHeld(PlayerItemHeldEvent event) {
		Handlers.handleItemHeld(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Handlers.handleInteract(event);
		Handlers.handlerInteractLR(event);
		Handlers.handleLRUseInteract(event);
		Handlers.handleUseInteract(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Handlers.handleInteractEntity(event);
		Handlers.handleUseInteractEntity(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		Handlers.handleBlockBreak(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		Handlers.handleBlockPlace(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onInventoryClick(InventoryClickEvent event) {
		Handlers.handleNoHoldInvClick(event);
		Handlers.handleNoBrewInvClick(event);
		Handlers.handleNoWearInvClick(event);
		Handlers.handleNoCookInvClick(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemCraft(CraftItemEvent event) {
		Handlers.handleItemCraft(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Handlers.handlePlayerDeath(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Handlers.handlePlayerSpawn(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onItemEnchant(EnchantItemEvent event) {
		Handlers.handleEnchantItem(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		Handlers.handlePlayerDamageEntity(event);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerShearEntity(PlayerShearEntityEvent event) {
		Handlers.handlePlayerShearEntity(event);
	}
}
