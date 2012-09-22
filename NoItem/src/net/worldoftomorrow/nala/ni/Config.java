package net.worldoftomorrow.nala.ni;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	
	private NoItem plugin;
	private File confFile;
	private static FileConfiguration conf;
	
	private static HashMap<String, Object> values = new HashMap<String, Object>();
	private String header = "";
	
	public Config(NoItem plugin) {
		StringBuilder headBuilder = new StringBuilder();
		headBuilder.append("## Notify Message Variables ##" + "\n");
		headBuilder.append("%n = Player name" + "\n");
		headBuilder.append("%i = Item/Recipe" + "\n");
		headBuilder.append("%x = X location" + "\n");
		headBuilder.append("%y = Y location" + "\n");
		headBuilder.append("%z = Z location" + "\n");
		headBuilder.append("%w = World" + "\n");
		headBuilder.append("Admin Message Specific Variables:" + "\n");
		headBuilder.append("%t = Event type (i.e. BREW, CRAFT, SMELT)" + "\n");
		headBuilder.append("\n");
		headBuilder.append("To block a potion, you must enter the damage value of the potion and ingredient needed." + "\n");
		headBuilder.append("Recipes can be found here: http://www.minecraftwiki.net/wiki/Brewing#Recipes" + "\n");
		headBuilder.append("Potion data values here: http://www.minecraftwiki.net/wiki/Potions#Data_value_table" + "\n");
		headBuilder.append("Ingredients here: http://www.minecraftwiki.net/wiki/Brewing#Base_Ingredients" + "\n");
		headBuilder.append("\n");
		headBuilder.append("For a list permissions, go here: http://dev.bukkit.org/server-mods/noitem/pages/permissions/" + "\n");
		this.header = headBuilder.toString();

		values.put("Notify.Admin", false);
		values.put("Notify.NoUse", true);
		values.put("Notify.NoBrew", true);
		values.put("Notify.NoHold", true);
		values.put("Notify.NoWear", true);
		values.put("Notify.NoCraft", true);
		values.put("Notify.NoPickup", true);
		values.put("Notify.NoCook", true);
		values.put("Notify.NoDrop", true);
		values.put("Notify.NoBreak", true);
		values.put("Notify.NoPlace", true);
		values.put("Notify.NoDrink", true);
		values.put("Notify.NoOpen", true);
		values.put("Notify.NoHave", true);
		values.put("Notify.NoEnchant", true);
		values.put("Messages.Admin", "&e%n &9tried to &c%t %i &9in world %w @ &a%x,%y,%z");
		values.put("Messages.NoUse", "&9You are not allowed to use a(n) &4%i&9!");
		values.put("Messages.NoBrew", "You are not allowed to brew that potion! &4(%i)");
		values.put("Messages.NoHold", "You are not allowed to hold &4%i&9!");
		values.put("Messages.NoWear", "You are not allowed to wear &4%i&9!");
		values.put("Messages.NoCraft", "You are not allowed to craft &4%i&9.");
		values.put("Messages.NoPickup", "You are not allowed to pick that up! (%i)");
		values.put("Messages.NoCook", "You are not allowed to cook &4%i&9.");
		values.put("Messages.NoDrop", "You are not allowed to drop &4%i.");
		values.put("Messages.NoBreak", "You are not allowed to break &4%i.");
		values.put("Messages.NoPlace", "You are not allowed to place &4%i.");
		values.put("Messages.NoDrink", "You are not allowed to drink that!");
		values.put("Messages.NoOpen", "You are not allowed to open that!");
		values.put("Messages.NoHave", "You are not allowed to have that item!");
		values.put("Messages.NoEnchant", "You are not allowed to use the enchantment &4%i");
		values.put("Debugging", false);
		values.put("PluginChannel", "main");
		values.put("CheckForUpdates", true);
		values.put("ConfigurationVersion", "1.2");
		
		this.plugin = plugin;
		this.confFile = new File(plugin.getDataFolder(), "config.yml");
		try {
			if(!confFile.exists() && !confFile.createNewFile())
				Log.severe("Could not load configuration!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.loadConfig();
		this.getSetValues();
		try {
			this.writeConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.loadConfig();
	}
	
	private void writeConfig() throws IOException {
		if(confFile.delete() && confFile.createNewFile()) {
		conf.options().header(this.header);
		conf.options().copyHeader(true);
		for(String key : values.keySet()) {
			conf.set(key, values.get(key));
		}
		conf.save(confFile);
		}
	}
	
	public void loadConfig() {
		conf = plugin.getConfig();
	}
	
	private void getSetValues() {
		Map<String, Object> values = conf.getValues(true);
		//double version = conf.getDouble("ConfigurationVersion", 0.0);
		for(String key : values.keySet()) {
			if(key.equals("ConfigurationVersion")) {
				continue;
			}
			if(conf.isSet(key))
				values.put(key, conf.get(key));
		}
	}
	
	public static Object getValue(String key) {
		return conf.get(key);
	}
	
	public static boolean getBoolean(String key) {
		return conf.getBoolean(key);
	}
	
	public static String getString(String key) {
		return conf.getString(key);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getValues() {
		return (Map<String, Object>) values.clone();
	}
	
}
