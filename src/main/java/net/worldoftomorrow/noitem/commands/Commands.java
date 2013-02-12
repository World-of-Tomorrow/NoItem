package net.worldoftomorrow.noitem.commands;

import net.worldoftomorrow.noitem.Config;
import net.worldoftomorrow.noitem.NoItem;
import net.worldoftomorrow.noitem.permissions.Perm;
import net.worldoftomorrow.noitem.permissions.VaultHook;
import net.worldoftomorrow.noitem.util.DebugDump;
import net.worldoftomorrow.noitem.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands {
	protected static void reload(CommandSender sender) {
		if(!canRunCommand(sender, Perm.CMD_RELOAD)) {
			sender.sendMessage(ChatColor.RED + "You are not allowed to run that command.");
		} else {
			NoItem.getInstance().reloadConfig();
			NoItem.getPermsManager().pawl = NoItem.getInstance().getConfig().getBoolean("PermsAsWhiteList");
			new VaultHook();
			sender.sendMessage(ChatColor.AQUA + "Configuration reloaded.");
		}
	}
	
	protected static void version(CommandSender sender) {
		if(!canRunCommand(sender, Perm.CMD_VERSION)) {
			sender.sendMessage(ChatColor.RED + "You are not allowed to run that command.");
		} else {
			sender.sendMessage(ChatColor.AQUA + "NoItem Version: " + NoItem.getInstance().getDescription().getVersion());
		}
	}
	
	protected static void debug(String arg, CommandSender sender) {
		if(arg.equalsIgnoreCase("dump") && canRunCommand(sender, Perm.CMD_DBG_DUMP)) {
			DebugDump.dump(NoItem.getInstance());
		} else if (arg.equalsIgnoreCase("toggle") && canRunCommand(sender, Perm.CMD_DBG_TOGGLE)) {
			NoItem.getInstance().getConfig().set("Debugging", !Config.getBoolean("Debugging"));
			NoItem.getInstance().reloadConfig();
			sender.sendMessage("&cNoItem debugging toggled, " + Config.getBoolean("Debugging"));
		}
	}
	
	private static boolean canRunCommand(CommandSender sender, String perm) {
		if(!(sender instanceof Player) || !Util.playerHasPerm((Player) sender, perm))
			return true;
		else 
			return false;
	}
}
