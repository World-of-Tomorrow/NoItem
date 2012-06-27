package net.worldoftomorrow.nala.ni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public boolean loaded = false;

    /*--Config Version--*/
    private final double configVersion = 1.0;

    /*--Defaults--*/
    private boolean notifyAdmins = false;
    private boolean notifyNoUse = true;
    private boolean notifyNoBrew = true;
    private boolean notifyNoHold = true;
    private boolean notifyNoWear = true;
    private boolean notifyNoCraft = true;
    private boolean notifyNoPickup = true;
    private boolean notifyNoCook = true;
    private boolean notifyNoDrop = true;

    private boolean stopArmorWear = true;
    private boolean stopToolUse = true;
    private boolean stopCrafting = true;
    private boolean stopItemPickup = true;
    private boolean stopPotionBrew = true;
    private boolean stopItemHold = true;
    private boolean stopCook = true;
    private boolean stopItemDrop = true;

    private boolean perItemPermissions = true;
    private boolean debugging = false;
    private String pluginChannel = "main";

    private String adminMessage = "&e%n &9tried to &c%t %i &9in world %w @ &a%x,%y,%z";
    private String noUseMessage = "&9You are not allowed to use a(n) &4%i&9!";
    private String noBrewMessage = "You are not allowed to brew that potion! &4(%i)";
    private String noHoldMessage = "You are not allowed to hold &4%i&9!";
    private String noWearMessage = "You are not allowed to wear &4%i&9!";
    private String noCraftMessage = "You are not allowed to craft &4%i&9.";
    private String noPickupMessage = "You are not allowed to pick that up! (%i)";
    private String noCookMessage = "You are not allowed to cook &4%i&9.";
    private String noDropMessage = "You are not allowed to drop &4%i.";

    private List<String> disallowedCrafting = new ArrayList<String>();
    private List<String> disallowedPotionRecipes = new ArrayList<String>();
    /*----------*/
    private NoItem plugin;
    private PrintWriter out = null;
    private File confFile;
    private static FileConfiguration conf = null;

    public Config(NoItem plugin) {
	this.plugin = plugin;
	if (!this.plugin.getDataFolder().exists()) {
	    this.plugin.getDataFolder().mkdirs();
	}
	this.confFile = new File(this.plugin.getDataFolder(), "config.yml");
	if (!confFile.exists()) { // If the config does not exist
	    try {
		if (confFile.createNewFile()) { // Try to create the file +
						// directories
		    this.writeConfig(); // Write the config
		    this.loadConfig(); // Load the config
		} else { // If making the directory fails, give an error
		    Log.severe("Could not create configuration file!");
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else { // If the config file does exist
	    this.loadConfig(); // Load possibly old or damaged the config.
	    this.updateConfig(); // Update/Fix it.
	    this.loadConfig(); // Reload the updated config.
	}
    }

    private void writeConfig() {
	try {
	    out = new PrintWriter(this.confFile);
	    out.println("# Notify Message Variables:");
	    out.println("# %n = Player name");
	    out.println("# %i = Item/Recipe");
	    out.println("# %x = X location");
	    out.println("# %y = Y location");
	    out.println("# %z = Z location");
	    out.println("# %w = World");
	    out.println("# Admin Message Specfific Variables:");
	    out.println("# %t = Event type (i.e. BREW, CRAFT, SMELT)");
	    out.println("");
	    out.println("Notify:");
	    out.println("    Admins: " + notifyAdmins);
	    // Insert the 's here otherwise it will cause problems when updating
	    // Because the getter methods do not grab them
	    out.println("    AdminMessage: \'" + adminMessage + "\'");
	    out.println("    NoUse: " + notifyNoUse);
	    out.println("    NoUseMessage: \'" + noUseMessage + "\'");
	    out.println("    NoBrew: " + notifyNoBrew);
	    out.println("    NoBrewMessage: \'" + noBrewMessage + "\'");
	    out.println("    NoHold: " + notifyNoHold);
	    out.println("    NoHoldMessage: \'" + noHoldMessage + "\'");
	    out.println("    NoWear: " + notifyNoWear);
	    out.println("    NoWearMessage: \'" + noWearMessage + "\'");
	    out.println("    NoCraft: " + notifyNoCraft);
	    out.println("    NoCraftMessage: \'" + noCraftMessage + "\'");
	    out.println("    NoPickup: " + notifyNoPickup);
	    out.println("    NoPickupMessage: \'" + noPickupMessage + "\'");
	    out.println("    NoCook: " + notifyNoCook);
	    out.println("    NoCookMessage: \'" + noCookMessage + "\'");
	    out.println("    NoDrop: " + notifyNoDrop);
	    out.println("    NoDropMessage: \'" + noDropMessage + "\'");
	    out.println("");
	    out.println("# Blocked items list ( itemID:DamageValue )    ");
	    out.println("DisallowedCraftingRecipes:");
	    if (disallowedCrafting.isEmpty()) {
		out.println("    - '5'");
		out.println("    - '5:1'");
		out.println("    - '5:2'");
	    } else {
		for (String item : disallowedCrafting) {
		    out.println("    - '" + item + "'");
		}
	    }
	    out.println();
	    out.println("# To block a potion, you must enter the damage value of the potion and ingredient needed.");
	    out.println("# Recipes can be found here: http://www.minecraftwiki.net/wiki/Brewing");
	    out.println("# Here are a few potions:");
	    out.println();
	    out.println("# Water Bottle - 0");
	    out.println("# Awkward Potion - 16");
	    out.println("# Thick Potion - 32");
	    out.println("# Mundane Potion (Extended) - 64");
	    out.println("# Mundane Potion - 8192");
	    out.println("# Potion of Regeneration (2:00) - 8193");
	    out.println("# Potion of Regeneration (8:00) - 8257");
	    out.println("# Potion of Regeneration II - 8225");
	    out.println("# Potion of Swiftness(3:00) - 8194");
	    out.println("# Potion of Swiftness (8:00) - 8258");
	    out.println("# Potion of Swiftness II - 8226");
	    out.println("# Potion of Fire Resistance (3:00) - 8195");
	    out.println("# Potion of Fire Resistance (8:00) - 8259");
	    out.println("# Potion of Fire Resistance (reverted) - 8227");
	    out.println();
	    out.println("# The rest can be found here: http://www.minecraftwiki.net/wiki/Potions#Base_Potions");
	    out.println();
	    out.println("# Here are are the Ingredients:");
	    out.println();
	    out.println("# Nether Wart - 372");
	    out.println("# Glowstone Dust - 348");
	    out.println("# Redstone Dust - 331");
	    out.println("# Fermented Spider Eye - 376");
	    out.println("# Magma Cream - 378");
	    out.println("# Sugar - 353");
	    out.println("# Glistering Melon - 382");
	    out.println("# Spider Eye - 375");
	    out.println("# Ghast Tear - 370");
	    out.println("# Blaze Powder - 377");
	    out.println("# Gun Powder - 289");
	    out.println();
	    out.println("# Default example is 0:372 which would block the Awkward Potion");
	    out.println("DisallowedPotionRecipes:");
	    if (disallowedPotionRecipes.isEmpty()) {
		out.println("    - '0:372'");
	    } else {
		for (String recipe : disallowedPotionRecipes) {
		    out.println("    - '" + recipe + "'");
		}
	    }
	    out.println();
	    out.println("#Use these to turn off individual features");
	    out.println("StopCrafting: " + stopCrafting);
	    out.println("StopItemPickup: " + stopItemPickup);
	    out.println("StopPotionBrew: " + stopPotionBrew);
	    out.println("StopToolUse: " + stopToolUse);
	    out.println("StopItemHold: " + stopItemHold);
	    out.println("StopArmorWear: " + stopArmorWear);
	    out.println("StopItemCook: " + stopCook);
	    out.println("StopItemDrop: " + stopItemDrop);
	    out.println();
	    out.println("# Permissions:");
	    out.println("# noitem.allitems");
	    out.println("# noitem.admin");
	    out.println("# noitem.nopickup.<itemId>[.dataValue]");
	    out.println("# noitem.nocraft.<itemId>[.dataValue]");
	    out.println("# noitem.nobrew.<potionDV>.<IngredientID>");
	    out.println("# noitem.nouse.<itemID> or noitem.nouse.<itemName>");
	    out.println("# noitem.nohold.<itemID> or noitem.nohold.<itemName>");
	    out.println("# noitem.nowear.<itemID> or noitem.nowear.<itemName>");
	    out.println("# noitem.nocook.<itemID> or noitem.nocook.<itemName>");
	    out.println("# noitem.nodrop.<itemID>[.dataValue]");
	    out.println();
	    out.println("PerItemPermissions: " + perItemPermissions);
	    out.println();
	    out.println("#Don't turn this on unless you like getting spammed with messages!");
	    out.println("Debugging: " + debugging);
	    out.println();
	    out.println("# This is to change whether you recieve update notifications");
	    out.println("# for recommended builds or for development builds. (main/dev)");
	    out.println("PluginChannel: " + pluginChannel);
	    out.println();
	    out.println("ConfigurationVersion: " + configVersion);
	    out.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public void loadConfig() {
	conf = plugin.getConfig();
	this.updateConfig();
	this.loaded = true;
    }

    private void updateConfig() {
	if (conf.isSet("Notify.Admins")) {
	    this.notifyAdmins = conf.getBoolean("Notify.Admins");
	}
	if (conf.isSet("Notify.NoUse")) {
	    this.notifyNoUse = conf.getBoolean("Notify.NoUse");
	}
	if (conf.isSet("Notify.NoBrew")) {
	    this.notifyNoBrew = conf.getBoolean("Notify.NoBrew");
	}
	if (conf.isSet("Notify.NoHold")) {
	    this.notifyNoHold = conf.getBoolean("Notify.NoHold");
	}
	if (conf.isSet("Notify.NoWear")) {
	    this.notifyNoWear = conf.getBoolean("Notify.NoWear");
	}
	if (conf.isSet("Notify.NoCraft")) {
	    this.notifyNoCraft = conf.getBoolean("Notify.NoCraft");
	}
	if (conf.isSet("Notify.NoPickup")) {
	    this.notifyNoPickup = conf.getBoolean("Notify.NoPickup");
	}
	if (conf.isSet("Notify.NoCook")) {
	    this.notifyNoCook = conf.getBoolean("Notify.NoCook");
	}
	if (conf.isSet("Notify.NoDrop")) {
	    this.notifyNoDrop = conf.getBoolean("Notify.NoDrop");
	}
	if (conf.isSet("StopCrafting")) {
	    this.stopCrafting = conf.getBoolean("StopCrafting");
	}
	if (conf.isSet("StopItemPickup")) {
	    this.stopItemPickup = conf.getBoolean("StopItemPickup");
	}
	if (conf.isSet("StopPotionBrew")) {
	    this.stopPotionBrew = conf.getBoolean("StopPotionBrew");
	}
	if (conf.isSet("StopToolUse")) {
	    this.stopToolUse = conf.getBoolean("StopToolUse");
	}
	if (conf.isSet("StopItemHold")) {
	    this.stopItemHold = conf.getBoolean("StopItemHold");
	}
	if (conf.isSet("StopArmorWear")) {
	    this.stopArmorWear = conf.getBoolean("StopArmorWear");
	}
	if (conf.isSet("StopItemCook")) {
	    this.stopCook = conf.getBoolean("StopItemCook");
	}
	if (conf.isSet("StopItemDrop")) {
	    this.stopItemDrop = conf.getBoolean("StopItemDrop");
	}
	if (conf.isSet("PerItemPermissions")) {
	    this.perItemPermissions = conf.getBoolean("PerItemPermissions");
	}
	if (conf.isSet("Debugging")) {
	    this.debugging = conf.getBoolean("Debugging");
	}
	if (conf.isSet("PluginChannel")) {
	    this.pluginChannel = conf.getString("PluginChannel");
	}
	if (conf.isSet("DisallowedCraftingRecipes")) {
	    this.disallowedCrafting = conf
		    .getStringList("DisallowedCraftingRecipes");
	}
	if (conf.isSet("DisallowedPotionRecipes")) {
	    this.disallowedPotionRecipes = conf
		    .getStringList("DisallowedPotionRecipes");
	}
	if (conf.isSet("Notify.AdminMessage")) {
	    this.adminMessage = conf.getString("Notify.AdminMessage");
	}
	if (conf.isSet("Notify.NoUseMessage")) {
	    this.noUseMessage = conf.getString("Notify.NoUseMessage");
	}
	if (conf.isSet("Notify.NoBrewMessage")) {
	    this.noBrewMessage = conf.getString("Notify.NoBrewMessage");
	}
	if (conf.isSet("Notify.NoHoldMessage")) {
	    this.noHoldMessage = conf.getString("Notify.NoHoldMessage");
	}
	if (conf.isSet("Notify.NoWearMessage")) {
	    this.noWearMessage = conf.getString("Notify.NoWearMessage");
	}
	if (conf.isSet("Notify.NoCraftMessage")) {
	    this.noCraftMessage = conf.getString("Notify.NoCraftMessage");
	}
	if (conf.isSet("Notify.NoPickupMessage")) {
	    this.noPickupMessage = conf.getString("Notify.NoPickupMessage");
	}
	if (conf.isSet("Notify.NoCookMessage")) {
	    this.noCookMessage = conf.getString("Notify.NoCookMessage");
	}
	if (conf.isSet("Notify.NoDropMessage")) {
	    this.noDropMessage = conf.getString("Notify.NoDropMessage");
	}

	this.writeConfig(); // Load all the variables that are set in the
			    // config. Then update it.
    }

    public static boolean notifyAdmins() {
	return conf.getBoolean("Notify.Admins");
    }

    public static boolean notifyNoUse() {
	return conf.getBoolean("Notify.NoUse");
    }

    public static boolean notifyNoBrew() {
	return conf.getBoolean("Notify.NoBrew");
    }

    public static boolean notifyNoHold() {
	return conf.getBoolean("Notify.NoHold");
    }

    public static boolean notfiyNoWear() {
	return conf.getBoolean("Notify.NoWear");
    }

    public static boolean notifyNoCraft() {
	return conf.getBoolean("Notify.NoCraft");
    }

    public static boolean notifyNoPickup() {
	return conf.getBoolean("Notify.NoPickup");
    }

    public static boolean notifyNoCook() {
	return conf.getBoolean("Notify.NoCook");
    }

    public static boolean notifyNoDrop() {
	return conf.getBoolean("Notify.NoDrop");
    }

    // Stop//
    public static boolean stopCrafting() {
	return conf.getBoolean("StopCrafting");
    }

    public static boolean stopItemPickup() {
	return conf.getBoolean("StopItemPickup");
    }

    public static boolean stopPotionBrew() {
	return conf.getBoolean("StopPotionBrew");
    }

    public static boolean stopToolUse() {
	return conf.getBoolean("StopToolUse");
    }

    public static boolean stopItemHold() {
	return conf.getBoolean("StopItemHold");
    }

    public static boolean stopArmourWear() {
	return conf.getBoolean("StopArmourWear");
    }

    public static boolean stopItemCook() {
	return conf.getBoolean("StopItemCook");
    }

    public static boolean stopItemDrop() {
	return conf.getBoolean("StopItemDrop");
    }

    // Misc//
    public static boolean debugging() {
	return conf.getBoolean("Debugging");
    }

    public static boolean perItemPerms() {
	return conf.getBoolean("PerItemPermissions");
    }

    public static String pluginChannel() {
	return conf.getString("PluginChannel");
    }

    // Message//
    public static String adminMessage() {
	return conf.getString("Notify.AdminMessage");
    }

    public static String noUseMessage() {
	return conf.getString("Notify.NoUseMessage");
    }

    public static String noBrewMessage() {
	return conf.getString("Notify.NoBrewMessage");
    }

    public static String noHoldMessage() {
	return conf.getString("Notify.NoHoldMessage");
    }

    public static String noWearMessage() {
	return conf.getString("Notify.NoWearMessage");
    }

    public static String noCraftMessage() {
	return conf.getString("Notify.NoCraftMessage");
    }

    public static String noPickupMessage() {
	return conf.getString("Notify.NoPickupMessage");
    }

    public static String noCookMessage() {
	return conf.getString("Notify.NoCookMessage");
    }

    public static String noDropMessage() {
	return conf.getString("Notify.NoDropMessage");
    }

    // Lists//
    public static List<String> disallowedCrafting() {
	return conf.getStringList("DisallowedCraftingRecipes");
    }

    public static List<String> disallowedPotions() {
	return conf.getStringList("DisallowedPotionRecipes");
    }

    public static Object getValue(String path) {
	return conf.get(path);
    }
}
