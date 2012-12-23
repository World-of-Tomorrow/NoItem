package net.worldoftomorrow.noitem.util;

import net.worldoftomorrow.noitem.Config;
import net.worldoftomorrow.noitem.NoItem;

public class Dbg {
	public static void $(String msg) {
		if(Config.getBoolean("Debugging")) {
			NoItem.getInstance().getLogger().info(msg);
		}
	}
}
