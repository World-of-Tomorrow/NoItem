package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener {
	
	private static Log log = new Log();

	private List<String> rawItems = Configuration.disallowedItems();

	private List<String> dItems = new ArrayList<String>();

	private boolean itemsAdded = false;

	private void addItems() {
		for (String raw : rawItems) {
			if (raw.contains(":")) {
				dItems.add(raw);
			} else {
				String n = raw.concat(":0");
				dItems.add(n);
			}
		}
	}

	private boolean notifyPlayer = Configuration.notifyPlayer();

	private boolean notifyAdmin = Configuration.notifyAdmins();

	private boolean perItemPerms = Configuration.perItemPerms();

	private boolean debug = Configuration.debugging();

	private String pm = Configuration.playerMessage();

	private String am = Configuration.adminMessage();

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if (!itemsAdded) {
			addItems();
			itemsAdded = true;
		}

		if (debug) {
			log.log(
					"PlayerPickupItemEvent fired. picked up: "
							+ event.getItem().getItemStack().getTypeId());
		}

		Player p = event.getPlayer();
		int iid = event.getItem().getItemStack().getTypeId();
		int dv = (short) event.getItem().getItemStack().getDurability();

		if (!this.perItemPerms) { // If you the list should be used
			if (dItems.contains(iid + ":" + dv)) {
				if ((!Permissions.ALLITEMS.has(p)) || (!p.isOp())) {
					event.setCancelled(true);
					// Set the pickup delay to prevent chat overload.
					event.getItem().setPickupDelay(100);
					if (this.notifyPlayer) {
						notifyPlayer(p, iid);
					}
					if (this.notifyAdmin)
						notifyAdmin(p, iid);
				}
			} else if (debug) {
				p.sendMessage("This item can be picked up.");
			}
			
			/*--Per Item Permissions--*/
		} else {
			// If there is no data value
			if (dv == 0 && Permissions.NOPICKUP.has(p, iid)
					&& !Permissions.ALLITEMS.has(p)) {
				event.setCancelled(true);
				event.getItem().setPickupDelay(100);
				if (notifyPlayer) {
					notifyPlayer(p, iid);
				}
				if (notifyAdmin) {
					notifyAdmin(p, iid);
				}
			}
			//If there IS a data value
			if (dv != 0 && Permissions.NOPICKUP.has(p, iid, dv)
					&& !Permissions.ALLITEMS.has(p)) {
				event.setCancelled(true);
				event.getItem().setPickupDelay(100);
				if (notifyPlayer) {
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
			if ((player.isOp()) || (Permissions.ADMIN.has(player)))
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ formatedMessage);
	}
}