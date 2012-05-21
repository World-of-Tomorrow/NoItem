package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Tools {
	WOOD_SWORD("woodsword", 268, "Wooden Sword"),
	WOOD_SHOVEL("woodshovel", 269, "Wooden Shovel"),
	WOOD_PICKAXE("woodpickaxe", 270, "Wooden Pickaxe"),
	WOOD_AXE("woodaxe", 271, "Wooden Axe"),
	WOOD_HOE("woodhoe", 290, "Wooden Hoe"),
	
	STONE_SWORD("stonesword", 272, "Stone Sword"),
	STONE_SHOVEL("stoneshovel", 273, "Stone Shovel"),
	STONE_PICKAXE("stonepickaxe", 274, "Stone Pickaxe"),
	STONE_AXE("stoneaxe", 275, "Stone Axe"),
	STONE_HOE("stonehoe", 291, "Stone Hoe"),
	
	IRON_SWORD("ironsword", 267, "Iron Sword"),
	IRON_SHOVEL("ironshovel", 256, "Iron Shovel"),
	IRON_PICKAXE("ironpickaxe", 257, "Iron Pickaxe"),
	IRON_AXE("ironaxe", 258, "Iron Axe"),
	IRON_HOE("ironhoe", 292, "Iron Hoe"),
	
	DIAMOND_SWORD("diamondsword", 276, "Diamond Sword"),
	DIAMOND_SHOVEL("diamondshovel", 277, "Diamond Shovel"),
	DIAMOND_PICKAXE("diamondpickaxe", 278, "Diamond Pickaxe"),
	DIAMOND_AXE("diamondaxe", 279, "Diamond Axe"),
	DIAMOND_HOE("diamondhoe", 293, "Diamond Hoe"),
	
	GOLD_SWORD("goldsword", 283, "Golden Sword"),
	GOLD_SHOVEL("goldshovel", 284, "Golden Shovel"),
	GOLD_PICKAXE("goldpickaxe", 285, "Golden Pickaxe"),
	GOLD_AXE("goldaxe", 286, "Golden Axe"),
	GOLD_HOE("goldhoe", 294, "Golden Hoe"),
	
	SHEARS("shears", 359, "Shears"),
	FISHING_ROD("fishingrod", 346, "Fishing Rod"),
	BOW("bow", 261, "Bow");
	
	private final String name;
	private final int id;
	private final String realName;
	
	Tools(String name, int id, String realName){
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
	
	public static Tools getTool(int id){
		if(tools.containsKey(id)){
			return tools.get(id);	
		} else {
			return null;
		}
	}
	
	public static Tools getTool(String name){
		if(names.containsKey(name)){
			return names.get(name);
		} else {
			return null;
		}
	}
	
	public static Map<Tools, Integer> ids = new HashMap<Tools, Integer>();
	public static Map<Integer, Tools> tools = new HashMap<Integer, Tools>();
	public static Map<String, Tools> names = new HashMap<String, Tools>();
	public static Map<Tools, String> realNames = new HashMap<Tools, String>();
	
	static{
		for(Tools tool : EnumSet.allOf(Tools.class)){
			ids.put(tool, tool.id);
			tools.put(tool.id, tool);
			names.put(tool.name, tool);
			realNames.put(tool, tool.realName);
		}
	}
}
