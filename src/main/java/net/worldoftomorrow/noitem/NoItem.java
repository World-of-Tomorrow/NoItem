package net.worldoftomorrow.noitem;

/*
 * Things To Implement:
 * 	- "/noitem check" command
 */

import java.io.IOException;
import java.util.logging.Logger;

import net.worldoftomorrow.noitem.commands.CmdNoItem;
import net.worldoftomorrow.noitem.events.Listeners;
import net.worldoftomorrow.noitem.lists.Lists;
import net.worldoftomorrow.noitem.permissions.PermMan;
import net.worldoftomorrow.noitem.permissions.VaultHook;
import net.worldoftomorrow.noitem.util.Metrics;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoItem extends JavaPlugin {
	
	private static NoItem instance;
	private static PermMan permsManager;
	private static Config config;
	private static Lists lists;
	
	@Override
	public void onEnable() {
		setupStatic(this);
		this.getCommand("noitem").setExecutor(new CmdNoItem());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listeners(), this);
		new VaultHook();
		try {
			if(!new Metrics(this).start()) {
				this.getLogger().warning("Could not start metrics!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static NoItem getInstance() {
		return instance;
	}
	
	public static Logger log() {
		return NoItem.getInstance().getLogger();
	}
	
	public static void log(String msg) {
		NoItem.getInstance().getLogger().info(msg);
	}
	
	public static PermMan getPermsManager() {
		return permsManager;
	}
	
	public static Lists getLists() {
		return lists;
	}
	
	public Config getConfigManager() {
		return config;
	}
	
	private static void setupStatic(NoItem instance) {
		NoItem.instance = instance;
		NoItem.config = new Config();
		NoItem.permsManager = new PermMan();
		NoItem.lists = new Lists();
	}
}
