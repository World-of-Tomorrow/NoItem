package net.worldoftomorrow.nala.ni.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Log;
import net.worldoftomorrow.nala.ni.NoItem;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class DropListener implements Listener {
	
	private NoItem plugin = NoItem.getPlugin();

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Log.debug("ItemDropEvent fired.");
		Player p = event.getPlayer();
		ItemStack stack = event.getItemDrop().getItemStack();
		if (Perms.NODROP.has(p, stack)) {
			Log.debug("Player has the permission node");
			event.setCancelled(true);
			StringHelper.notifyPlayer(p, EventTypes.DROP, stack);
			StringHelper.notifyAdmin(p, EventTypes.DROP, stack);
		} else {
			Log.debug("Player does not have the permission node");
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = event.getEntity().getPlayer();
		if (Perms.ONDEATH.has(p, "keep")) {
			List<ItemStack> drops = new ArrayList<ItemStack>(event.getDrops());
			event.getDrops().clear();
				plugin.getItemList().put(p.getName(), drops);
		} else if (Perms.ONDEATH.has(p, "remove")) {
			event.getDrops().clear();
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		List<ItemStack> remainder = new ArrayList<ItemStack>();
		if (plugin.getItemList().containsKey(p.getName())) {
			for(ItemStack i : plugin.getItemList().get(p.getName())) {
				Map<Integer, ItemStack> r = p.getInventory().addItem(i);
				if(!r.isEmpty()) {
					remainder.addAll(r.values());
				}
			}
			plugin.getItemList().remove(p.getName());
			if(!remainder.isEmpty()) {
				p.sendMessage(ChatColor.BLUE + "You have " + remainder.size() + " unclaimed items!");
				p.sendMessage(ChatColor.BLUE + "Make room in your inventory, then type \"/noitem claim\" to claim them!");
				plugin.getItemList().put(p.getName(), remainder);
			}
		}
	}
}
