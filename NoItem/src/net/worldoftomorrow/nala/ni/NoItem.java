package net.worldoftomorrow.nala.ni;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoItem extends JavaPlugin {
	private Log log = new Log();
	
	public Configuration config = new Configuration(this);
	VaultPerms perms;
	
	public void onEnable() {
		
		config.load();
		perms = new VaultPerms(this);
		
		Updater updater = new Updater(this.getDescription().getVersion());
		if(!updater.isLatest()){
			log.log("--------------------No Item--------------------");
			log.log("There is a new version ( " + updater.getLatest() + " ) available.");
			log.log("Download it at: " + this.getDescription().getWebsite());
			log.log("-----------------------------------------------");
		}
		
		PluginManager pm = getServer().getPluginManager();
		
		boolean craftListen = Configuration.stopCrafting();
		boolean pickupListen = Configuration.stopItemPickup();
		boolean brewListen = Configuration.stopPotionBrew();
		boolean toolListen = Configuration.stopToolUse();
		boolean holdListen = Configuration.stopItemHold();
		
		if (craftListen) {
			pm.registerEvents(new CraftingListener(), this);
		}
		if (pickupListen) {
			pm.registerEvents(new PickupListener(), this);
		}
		if (brewListen) {
			pm.registerEvents(new BrewingListener(), this);
		}
		if(toolListen){
			pm.registerEvents(new ToolListener(), this);
		}
		if(holdListen){
			pm.registerEvents(new InHandListener(), this);
		}
		this.log.log("[NoItem] Configs loaded, events registered, and cake baked.");
		
		if ((!craftListen) && (!pickupListen) && (!brewListen) && (!toolListen) && (!holdListen)) {
			this.log.log("[NoItem] No listeners active! Shutting down plugin.");
			getPluginLoader().disablePlugin(this);
		}
	}

	public void onDisable() {
		this.log.log("[NoItem] Configs unloaded, events unregisterededed, and cake eaten.");
	}
}