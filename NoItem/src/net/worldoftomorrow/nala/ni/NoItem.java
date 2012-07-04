package net.worldoftomorrow.nala.ni;

import java.io.IOException;

import net.worldoftomorrow.nala.ni.listeners.BlockListener;
import net.worldoftomorrow.nala.ni.listeners.CommandListener;
import net.worldoftomorrow.nala.ni.listeners.DropListener;
import net.worldoftomorrow.nala.ni.listeners.HoldListener;
import net.worldoftomorrow.nala.ni.listeners.InventoryListener;
import net.worldoftomorrow.nala.ni.listeners.JoinListener;
import net.worldoftomorrow.nala.ni.listeners.NoUseListener;
import net.worldoftomorrow.nala.ni.listeners.PickupListener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoItem extends JavaPlugin {

	private Config config;
	protected Vault vault;
	private static NoItem plugin;
	private Metrics metrics;

	public void onEnable() {
		plugin = this;
		this.config = new Config(this);
		this.vault = new Vault(this);
		CommandListener cl = new CommandListener(this);
		this.getCommand("noitem").setExecutor(cl);

		Updater updater = new Updater(this.getDescription().getVersion());
		if (!updater.isLatest()) {
			Log.info("--------------------No Item--------------------");
			Log.info("There is a new version ( " + updater.getLatest()
					+ " ) available.");
			Log.info("Download it at: " + this.getDescription().getWebsite());
			if (Config.pluginChannel().equalsIgnoreCase("dev")) {
				Log.info("You are using the development version channel!");
			}
			Log.info("-----------------------------------------------");
		}

		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new InventoryListener(), this);
		pm.registerEvents(new DropListener(), this);
		pm.registerEvents(new HoldListener(), this);
		pm.registerEvents(new NoUseListener(), this);
		pm.registerEvents(new PickupListener(), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new JoinListener(), this);

		try {
			metrics = new Metrics(this);
			metrics.start();
		} catch (IOException ex) {
		}
	}

	public void onDisable() {
	}

	public static NoItem getPlugin() {
		return plugin;
	}

	public Vault getVault() {
		return vault;
	}
	
	public Config getConfigClass() {
		return this.config;
	}
}