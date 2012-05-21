package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Armour {
	LEATHER_HELMET("leatherhelmet", 298),
	CHAIN_HELMET("chainhelmet", 302),
	IRON_HELMET("ironhelmet", 306),
	DIAMOND_HELMET("diamondhelmet", 310),
	GOLD_HELMET("goldhelmet", 314),
	
	LEATHER_CHESTPLATE("leatherchest", 299),
	CHAIN_CHESTPLATE("chainchest", 303),
	IRON_CHESTPLATE("ironchest", 307),
	DIAMOND_CHESTPLATE("diamondchest", 311),
	GOLD_CHESTPLATE("goldchest", 315),
	
	LEATHER_PANTS("leatherpants", 300),
	CHAIN_PANTS("chainpants", 304),
	IRON_PANTS("ironpants", 308),
	DIAMOND_PANTS("diamondpants", 312),
	GOLD_PANTS("goldpants", 316),
	
	LEATHER_BOOTS("leatherboots", 301),
	CHAIN_BOOTS("chainboots", 305),
	IRON_BOOTS("ironboots", 309),
	DIAMOND_BOOTS("diamondboots", 313),
	GOLD_BOOTS("goldboots", 317);
	
	private final String name;
	private final int id;
	
	private Armour(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static Armour getArmour(int id){
		return armours.get(id);
	}
	
	public static Armour getArmour(String name){
		return names.get(name);
	}
	
	public static Map<Armour, Integer> ids = new HashMap<Armour, Integer>();
	public static Map<Integer, Armour> armours = new HashMap<Integer, Armour>();
	public static Map<String, Armour> names = new HashMap<String, Armour>();
	
	static{
		for(Armour armour : EnumSet.allOf(Armour.class)){
			ids.put(armour, armour.id);
			armours.put(armour.id, armour);
			names.put(armour.name, armour);
		}
	}
}
