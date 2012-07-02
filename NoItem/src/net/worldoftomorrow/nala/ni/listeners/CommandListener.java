package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.NoItem;
import net.worldoftomorrow.nala.ni.Perms;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	NoItem plugin;

	public CommandListener(NoItem plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!Perms.ADMIN.has(p)) {
				p.sendMessage(ChatColor.RED + "[NI] "
						+ "You do not have permission to use this command!");
				return true;
			}
		}
		if (args.length <= 0) {
			sender.sendMessage(ChatColor.RED + "[NI] " + ChatColor.ITALIC
					+ "This server is running NoItem version: "
					+ ChatColor.UNDERLINE
					+ plugin.getDescription().getVersion());
			return false;
		} else {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.getConfigClass().reloadConfig();
				sender.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ "Configuration reloaded.");
				return true;
			} else {
				return false;
			}
		}
	}
}
