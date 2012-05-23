package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import net.milkbowl.vault.item.ItemInfo;
import net.milkbowl.vault.item.Items;

public class StringHelper {
	public static String replaceVars(String msg, Player p, int iid) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		if (Tools.getTool(iid) != null) {
			msg = msg.replace("%i", Tools.getTool(iid).getRealName());
		} else if (Armour.getArmour(iid) != null) {
			msg = msg.replace("%i", Armour.getArmour(iid).getRealName());
		} else if (Vault.vaultPerms) {
			ItemInfo info = Items.itemById(iid);
			msg = msg.replace("%i", info.getName());
		} else {
			String id = Integer.toString(iid);
			msg = msg.replace("%i", id);
		}
		return msg;
	}

	public static String replaceVars(String msg, Player p, String recipe) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		msg = msg.replace("%n", p.getDisplayName());
		msg = msg.replace("%w", p.getWorld().getName());
		msg = msg.replace("%x", x);
		msg = msg.replace("%y", y);
		msg = msg.replace("%z", z);
		msg.replace("%i", recipe);
		return msg;
	}
	
	public static void notifyPlayer(Player p, String msg, int id){
		p.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
				+ replaceVars(msg, p, id));
	}
	
	public static void notifyPlayer(Player p, String msg, String recipe){
		p.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
				+ replaceVars(msg, p, recipe));
	}
	
	public static void notifyAdmin(Player p, String msg, int iid) {
		String message = StringHelper.replaceVars(msg, p, iid);
		//TODO: separate option for log to console.
		//log.log(message);
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players)
			if (Perms.ADMIN.has(player)){
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ message);
			}
	}
	
	public static void notifyAdmin(Player p, String msg, String recipe) {
		String message = StringHelper.replaceVars(msg, p, recipe);
		//TODO: separate option for log to console.
		//log.log(message);
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players)
			if (Perms.ADMIN.has(player)){
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ message);
			}
	}
	//TODO: Chat color parsing
}