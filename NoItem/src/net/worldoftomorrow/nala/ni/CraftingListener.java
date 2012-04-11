package net.worldoftomorrow.nala.ni;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {
	private List<Integer> dItems = Config.getConfig(Config.ConfigFile.CONFIG)
			.getIntegerList("DisallowedItems");

	private boolean notifyPlayer = Config.getConfig(Config.ConfigFile.CONFIG)
			.getBoolean("Notify.Player");

	private boolean notifyAdmin = Config.getConfig(Config.ConfigFile.CONFIG)
			.getBoolean("Notify.Admins");

	private boolean perItemPerms = Config.getConfig(Config.ConfigFile.CONFIG)
			.getBoolean("PerItemPermissions");

	private boolean debug = Config.getConfig(Config.ConfigFile.CONFIG)
			.getBoolean("Debugging");

	private String pm = Config.getConfig(Config.ConfigFile.CONFIG).getString(
			"Notify.PlayerMessage");

	private String am = Config.getConfig(Config.ConfigFile.CONFIG).getString(
			"Notify.AdminMessage");

	private Log log = new Log();

	@EventHandler
	public void onItemCraft(CraftItemEvent event) {
		if (this.debug) {
			Bukkit.getServer().broadcastMessage(
					"CraftItemEvent fired. Blocked items: "
							+ this.dItems.size());
		}

		ItemStack item = event.getCurrentItem();
		int iid = item.getTypeId();
		String name = event.getWhoClicked().getName();
		Player p = Bukkit.getPlayer(name);

		if (!this.perItemPerms) {
			if (this.dItems.contains(iid)) {
				if ((!Permissions.ALLITEMS.has(p)) || (!p.isOp())) {
					event.setCancelled(true);

					if (this.notifyPlayer) {
						notifyPlayer(p, iid);
					}
					if (this.notifyAdmin)
						notifyAdmin(p, iid);
				}
			} else if (this.debug) {
				p.sendMessage("This item can be crafted.");
			}

		} else if ((Permissions.PERITEMCRAFT.has(p, iid))
				&& (!Permissions.ALLITEMS.has(p))) {
			event.setCancelled(true);
			if (this.notifyPlayer) {
				notifyPlayer(p, iid);
			}
			if (this.notifyAdmin)
				notifyAdmin(p, iid);
		} else if (this.debug) {
			p.sendMessage("This item can be crafted.");
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

		this.log.log(formatedMessage);
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players)
			if ((player.isOp()) || (Permissions.ADMIN.has(player)))
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ formatedMessage);
	}
}