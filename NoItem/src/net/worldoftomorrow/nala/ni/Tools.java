package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Tools {
	WOOD_SWORD("woodsword", 268),
	WOOD_SHOVEL("woodshovel", 269),
	WOOD_PICKAXE("woodpickaxe", 270),
	WOOD_AXE("woodaxe", 271),
	WOOD_HOE("woodhoe", 290),
	
	STONE_SWORD("stonesword", 272),
	STONE_SHOVEL("stoneshovel", 273),
	STONE_PICKAXE("stonepickaxe", 274),
	STONE_AXE("stoneaxe", 275),
	STONE_HOE("stonehoe", 291),
	
	IRON_SWORD("ironsword", 267),
	IRON_SHOVEL("ironshovel", 256),
	IRON_PICKAXE("ironpickaxe", 257),
	IRON_AXE("ironaxe", 258),
	IRON_HOE("ironhoe", 292),
	
	DIAMOND_SWORD("diamondsword", 276),
	DIAMOND_SHOVEL("diamondshovel", 277),
	DIAMOND_PICKAXE("diamondpickaxe", 278),
	DIAMOND_AXE("diamondaxe", 279),
	DIAMOND_HOE("diamondhoe", 293),
	
	GOLD_SWORD("goldsword", 283),
	GOLD_SHOVEL("goldshovel", 284),
	GOLD_PICKAXE("goldpickaxe", 285),
	GOLD_AXE("goldaxe", 286),
	GOLD_HOE("goldhoe", 294),
	
	SHEARS("shears", 359),
	FISHING_ROD("fishingrod", 346),
	BOW("bow", 261);
	
	private final String name;
	private final int id;
	
	Tools(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public static Tools getTool(int id){
		return tools.get(id);
	}
	
	public static Tools getTool(String name){
		return names.get(name);
	}
	
	public static Map<Tools, Integer> ids = new HashMap<Tools, Integer>();
	public static Map<Integer, Tools> tools = new HashMap<Integer, Tools>();
	public static Map<String, Tools> names = new HashMap<String, Tools>();
	
	static{
		for(Tools tool : EnumSet.allOf(Tools.class)){
			ids.put(tool, tool.id);
			tools.put(tool.id, tool);
			names.put(tool.name, tool);
		}
	}
}
