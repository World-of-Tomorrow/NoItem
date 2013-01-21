package net.worldoftomorrow.noitem.commands;

import net.worldoftomorrow.noitem.NoItem;
import net.worldoftomorrow.noitem.permissions.Perm;
import net.worldoftomorrow.noitem.permissions.VaultHook;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands {
	protected static void reload(CommandSender sender) {
		if(sender instanceof Player && !NoItem.getPermsManager().has((Player) sender, Perm.CMD_RELOAD)) {
			sender.sendMessage(ChatColor.RED + "You are not allowed to run that command.");
		} else {
			NoItem.getInstance().reloadConfig();
			NoItem.getPermsManager().pawl = NoItem.getInstance().getConfig().getBoolean("PermsAsWhiteList");
			new VaultHook();
			sender.sendMessage(ChatColor.AQUA + "Configuration reloaded.");
		}
	}
	
	protected static void version(CommandSender sender) {
		if(sender instanceof Player && !NoItem.getPermsManager().has((Player) sender, Perm.CMD_VERSION)) {
			sender.sendMessage(ChatColor.RED + "You are not allowed to run that command.");
		} else {
			sender.sendMessage(ChatColor.AQUA + "NoItem Version: " + NoItem.getInstance().getDescription().getVersion());
		}
	}
}
