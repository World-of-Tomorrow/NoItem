package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Cookable {
	CHICKEN("chicken", 365, "Chicken"),
	BEEF("beef", 363, "Beef"),
	PORK("pork", 319, "Porkchop"),
	FISH("fish", 349, "Fish"),
	LOG("log", 17, "Log"),
	IRON_ORE("ironore", 15, "Iron Ore"),
	GOLD_ORE("goldore", 14, "Gold Ore"),
	COBBLESTONE("cobblestone", 4, "Cobblestone"),
	SAND("sand", 12, "Sand"),
	CLAY("clay", 337, "Clay Ball"),
	CACTUS("cactus", 81, "Cactus");
	
	private final String name;
	private final int id;
	private final String realName;
	
	Cookable(String name, int id, String realName){
		this.name = name;
		this.id = id;
		this.realName = realName;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getRealName(){
		return realName;
	}
	
	public static Cookable getTool(int id){
		if(items.containsKey(id)){
			return items.get(id);	
		} else {
			return null;
		}
	}
	
	public static Cookable getTool(String name){
		if(names.containsKey(name)){
			return names.get(name);
		} else {
			return null;
		}
	}
	
	public static Map<Cookable, Integer> ids = new HashMap<Cookable, Integer>();
	public static Map<Integer, Cookable> items = new HashMap<Integer, Cookable>();
	public static Map<String, Cookable> names = new HashMap<String, Cookable>();
	public static Map<Cookable, String> realNames = new HashMap<Cookable, String>();
	
	static{
		for(Cookable item : EnumSet.allOf(Cookable.class)){
			ids.put(item, item.id);
			items.put(item.id, item);
			names.put(item.name, item);
			realNames.put(item, item.realName);
		}
	}
}
