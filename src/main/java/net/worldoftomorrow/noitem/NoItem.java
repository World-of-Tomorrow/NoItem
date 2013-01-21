package net.worldoftomorrow.noitem;

import java.io.IOException;
import java.util.logging.Logger;

import net.worldoftomorrow.noitem.commands.CmdNoItem;
import net.worldoftomorrow.noitem.events.Listeners;
import net.worldoftomorrow.noitem.lists.Lists;
import net.worldoftomorrow.noitem.permissions.PermMan;
import net.worldoftomorrow.noitem.permissions.VaultHook;
import net.worldoftomorrow.noitem.util.Metrics;
import net.worldoftomorrow.noitem.util.Updater;

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
		if(Config.getBoolean("Auto-Download-Updates")) {
			new Updater(this, this.getFile(), Updater.UpdateType.DEFAULT, true);
		} else if(Config.getBoolean("CheckForUpdates")) {
			new Updater(this, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, true);
		}
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
		NoItem.permsManager = new PermMan();
		NoItem.config = new Config();
		NoItem.lists = new Lists();
	}
}
