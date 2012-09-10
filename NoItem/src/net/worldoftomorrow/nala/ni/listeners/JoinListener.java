package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.NoItem;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.tasks.LoginTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class JoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		ItemStack stack = p.getItemInHand();
		BukkitScheduler scheduler = Bukkit.getScheduler();
		if (Perms.NOHOLD.has(p, stack)) {
			scheduler.scheduleSyncDelayedTask(NoItem.getPlugin(), new LoginTask(p),
					60L);
		}
	}
}
