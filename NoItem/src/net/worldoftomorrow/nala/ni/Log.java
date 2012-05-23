package net.worldoftomorrow.nala.ni;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	private static final Logger log = Logger.getLogger("Minecraft");

	public void log(Level l, String s) {
		log.log(l, s);
	}

	public void log(String s) {
		log.log(Level.INFO, s);
	}

	public void log(Level l, String s, Exception e, boolean printE) {
		if (printE)
			log.log(l, s, e);
		else
			log.log(l, s);
	}

	public void log(String s, Exception e, boolean printE) {
		if (printE)
			log.log(Level.SEVERE, s, e);
		else
			log.log(Level.SEVERE, s);
	}

	public void log(String s, Exception e) {
		log.log(Level.SEVERE, s, e);
	}
	
	public void debug(String s){
		if(Configuration.debugging()){
			this.log("[DEBUG] ".concat(s));
		}
	}
}