package net.worldoftomorrow.nala.ni;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoItem extends JavaPlugin {
	private Log log = new Log();

	public void onEnable() {
		Config.loadConfigs();
		PluginManager pm = getServer().getPluginManager();
		boolean craftListen = Config.getConfig(Config.ConfigFile.CONFIG)
				.getBoolean("StopCrafting");
		boolean pickupListen = Config.getConfig(Config.ConfigFile.CONFIG)
				.getBoolean("StopItemPickup");
		if (craftListen) {
			pm.registerEvents(new CraftingListener(), this);
		}
		if (pickupListen) {
			pm.registerEvents(new PickupListener(), this);
		}
		this.log.log("[NoItem] Configs loaded, events registered, and cake baked.");
		if ((!craftListen) && (!pickupListen)) {
			this.log.log("[NoItem] No listeners active! Shutting down plugin.");
			getPluginLoader().disablePlugin(this);
		}
	}

	public void onDisable() {
		this.log.log("[NoItem] Configs unloaded, events unregisterededed, and cake eaten.");
	}
}