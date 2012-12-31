package net.worldoftomorrow.noitem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdNoItem implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("reload")) {
				Commands.reload(sender);
			} else if(args[0].equalsIgnoreCase("check")) {
				
			}
		} else {
			Commands.version(sender);
		}
		return true;
	}

}
