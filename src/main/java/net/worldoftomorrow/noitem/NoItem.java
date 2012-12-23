package net.worldoftomorrow.noitem;

import net.worldoftomorrow.noitem.events.Listeners;
import net.worldoftomorrow.noitem.lists.Lists;
import net.worldoftomorrow.noitem.permissions.PermMan;

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
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listeners(), this);
	}
	
	public static NoItem getInstance() {
		return instance;
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
