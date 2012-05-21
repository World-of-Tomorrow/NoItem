package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Armour {
	LEATHER_HELMET("leatherhelmet", 298, "Leather Helmet"),
	CHAIN_HELMET("chainhelmet", 302, "Chain Helmet"),
	IRON_HELMET("ironhelmet", 306, "Iron Helmet"),
	DIAMOND_HELMET("diamondhelmet", 310, "Diamond Helmet"),
	GOLD_HELMET("goldhelmet", 314, "Golden Helmet"),
	
	LEATHER_CHESTPLATE("leatherchest", 299, "Leather Chestplate"),
	CHAIN_CHESTPLATE("chainchest", 303, "Chain Chestplate"),
	IRON_CHESTPLATE("ironchest", 307, "Iron Chestplate"),
	DIAMOND_CHESTPLATE("diamondchest", 311, "Diamond Chestplate"),
	GOLD_CHESTPLATE("goldchest", 315, "Golden Chestplate"),
	
	LEATHER_PANTS("leatherpants", 300, "Leather Pants"),
	CHAIN_PANTS("chainpants", 304, "Chain Pants"),
	IRON_PANTS("ironpants", 308, "Iron Pants"),
	DIAMOND_PANTS("diamondpants", 312, "Diamond Pants"),
	GOLD_PANTS("goldpants", 316, "Golden Pants"),
	
	LEATHER_BOOTS("leatherboots", 301, "Leather Boots"),
	CHAIN_BOOTS("chainboots", 305, "Chain Boots"),
	IRON_BOOTS("ironboots", 309, "Iron Boots"),
	DIAMOND_BOOTS("diamondboots", 313, "Diamond Boots"),
	GOLD_BOOTS("goldboots", 317, "Golden Boots");
	
	private final String name;
	private final int id;
	private final String realName;
	
	private Armour(String name, int id, String realName){
		this.name = name;
		this.id = id;
		this.realName = realName;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getRealName(){
		return realName;
	}
	
	public static Armour getArmour(int id){
		if(armours.containsKey(id)){
			return armours.get(id);
		} else {
			return null;
		}
	}
	
	public static Armour getArmour(String name){
		if(names.containsKey(name)){
			return names.get(name);
		} else {
			return null;
		}
	}
	
	public static Map<Armour, Integer> ids = new HashMap<Armour, Integer>();
	public static Map<Integer, Armour> armours = new HashMap<Integer, Armour>();
	public static Map<String, Armour> names = new HashMap<String, Armour>();
	public static Map<Armour, String> realNames = new HashMap<Armour, String>();
	
	static{
		for(Armour armour : EnumSet.allOf(Armour.class)){
			ids.put(armour, armour.id);
			armours.put(armour.id, armour);
			names.put(armour.name, armour);
			realNames.put(armour, armour.realName);
		}
	}
}
