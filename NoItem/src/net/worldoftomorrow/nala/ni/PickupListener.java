package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener {
	
	private static Log log = new Log();
	
	private boolean notifyNoPickup = Configuration.notifyNoPickup();

	private boolean notifyAdmin = Configuration.notifyAdmins();

	private boolean debug = Configuration.debugging();

	private String pm = Configuration.noPickupMessage();

	private String am = Configuration.adminMessage();

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if (debug) {
			log.log(
					"PlayerPickupItemEvent fired. picked up: "
							+ event.getItem().getItemStack().getTypeId());
		}

		Player p = event.getPlayer();
		int iid = event.getItem().getItemStack().getTypeId();
		int dv = (short) event.getItem().getItemStack().getDurability();
		// If there is no data value
		if (dv == 0 && VaultPerms.Permissions.NOPICKUP.has(p, iid)
				&& !VaultPerms.Permissions.ALLITEMS.has(p)) {
			event.setCancelled(true);
			event.getItem().setPickupDelay(100);
			if (notifyNoPickup) {
				notifyPlayer(p, iid);
			}
			if (notifyAdmin) {
				notifyAdmin(p, iid);
			}
		}
		// If there IS a data value
		if (dv != 0 && VaultPerms.Permissions.NOPICKUP.has(p, iid, dv)
				&& !VaultPerms.Permissions.ALLITEMS.has(p)) {
			event.setCancelled(true);
			event.getItem().setPickupDelay(100);
			if (notifyNoPickup) {
				notifyPlayer(p, iid);
			}
			if (notifyAdmin) {
				notifyAdmin(p, iid);
			}
		} else {
			if (debug) {
				p.sendMessage("This item can be picked up.");
			}
		}
	}

	
	private void notifyPlayer(Player p, int iid) {
		String dn = p.getDisplayName();
		String w = p.getWorld().getName();

		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();

		p.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
				+ StringHelper.replaceVars(this.pm, dn, w, x, y, z, iid));
	}

	private void notifyAdmin(Player p, int iid) {
		String dn = p.getDisplayName();
		String w = p.getWorld().getName();

		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		String formatedMessage = StringHelper.replaceVars(this.am, dn, w, x, y,
				z, iid);

		log.log(formatedMessage);
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players)
			if ((player.isOp()) || (VaultPerms.Permissions.ADMIN.has(player)))
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ formatedMessage);
	}
}