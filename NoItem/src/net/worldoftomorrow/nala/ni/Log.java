package net.worldoftomorrow.nala.ni;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	private static Logger log = NoItem.getPlugin().getLogger();

	public static void info(String s) {
		log.log(Level.INFO, s);
	}

	public static void severe(String s, Exception e) {
		log.log(Level.SEVERE, s, e);
	}

	public static void severe(String s) {
		log.log(Level.SEVERE, s);
	}

	public static void warn(String s) {
		log.log(Level.WARNING, s);
	}

	public static void warn(String s, Exception e) {
		log.log(Level.WARNING, s, e);
	}

	public static void debug(String s) {
		if (Config.getBoolean("Debugging")) {
			log.log(Level.INFO, s);
		}
	}

	public static void debug(String s, Exception e) {
		if (Config.getBoolean("Debugging")) {
			log.log(Level.INFO, s);
			e.printStackTrace();
		}
	}
}