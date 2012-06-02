package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Cookable {
	CHICKEN("chicken", 365, "Chicken", true),
	BEEF("beef", 363, "Beef", true),
	PORK("pork", 319, "Porkchop", true),
	FISH("fish", 349, "Fish", true),
	LOG("log", 17, "Log", false),
	IRON_ORE("ironore", 15, "Iron Ore", false),
	GOLD_ORE("goldore", 14, "Gold Ore", false),
	COBBLESTONE("cobblestone", 4, "Cobblestone", false),
	SAND("sand", 12, "Sand", false),
	CLAY("clay", 337, "Clay Ball", false),
	CACTUS("cactus", 81, "Cactus", false);
	
	private final String name;
	private final int id;
	private final String realName;
	private final boolean isFood;
	
	Cookable(String name, int id, String realName, boolean isFood){
		this.name = name;
		this.id = id;
		this.realName = realName;
		this.isFood = isFood;
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getRealName(){
		return this.realName;
	}
	
	public boolean isFood(){
		return this.isFood;
	}
	
	public static Cookable getItem(int id){
		if(items.containsKey(id)){
			return items.get(id);	
		} else {
			return null;
		}
	}
	
	public static Cookable getItem(String name){
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
