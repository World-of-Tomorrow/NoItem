package net.worldoftomorrow.nala.ni;

import net.worldoftomorrow.nala.ni.Config.ConfigFile;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoItem extends JavaPlugin {
	private Log log = new Log();
	
	private ConfigFile conf = Config.ConfigFile.CONFIG;

	public void onEnable() {
		Config.loadConfigs();
		
		Updater updater = new Updater(this.getDescription().getVersion());
		if(!updater.isLatest()){
			log.log("--------------------No Item--------------------");
			log.log("There is a new version ( " + updater.getLatest() + " ) available.");
			log.log("Download it at: " + this.getDescription().getWebsite());
			log.log("-----------------------------------------------");
		}
		
		PluginManager pm = getServer().getPluginManager();
		
		boolean craftListen = Config.getConfig(conf)
				.getBoolean("StopCrafting");
		boolean pickupListen = Config.getConfig(conf)
				.getBoolean("StopItemPickup");
		boolean brewListen = Config.getConfig(conf)
			.getBoolean("StopPotionBrew");
		
		if (craftListen) {
			pm.registerEvents(new CraftingListener(), this);
		}
		if (pickupListen) {
			pm.registerEvents(new PickupListener(), this);
		}
		if (brewListen) {
			pm.registerEvents(new BrewingListener(), this);
		}
		this.log.log("[NoItem] Configs loaded, events registered, and cake baked.");
		
		if ((!craftListen) && (!pickupListen) && (!brewListen)) {
			this.log.log("[NoItem] No listeners active! Shutting down plugin.");
			getPluginLoader().disablePlugin(this);
		}
	}

	public void onDisable() {
		this.log.log("[NoItem] Configs unloaded, events unregisterededed, and cake eaten.");
	}
}