package net.worldoftomorrow.nala.ni;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public enum Tools {
	WOOD_SWORD("woodsword", 268, "Wooden Sword", Material.WOOD_SWORD),
	WOOD_SHOVEL("woodshovel", 269, "Wooden Shovel", Material.WOOD_SPADE),
	WOOD_PICKAXE("woodpickaxe", 270, "Wooden Pickaxe", Material.WOOD_PICKAXE),
	WOOD_AXE("woodaxe", 271, "Wooden Axe", Material.WOOD_AXE),
	WOOD_HOE("woodhoe", 290, "Wooden Hoe", Material.WOOD_HOE),
	STONE_SWORD("stonesword", 272, "Stone Sword", Material.STONE_SWORD),
	STONE_SHOVEL("stoneshovel", 273, "Stone Shovel", Material.STONE_SPADE),
	STONE_PICKAXE("stonepickaxe", 274, "Stone Pickaxe", Material.STONE_PICKAXE),
	STONE_AXE("stoneaxe", 275, "Stone Axe", Material.STONE_AXE),
	STONE_HOE("stonehoe", 291, "Stone Hoe", Material.STONE_HOE),
	IRON_SWORD("ironsword", 267, "Iron Sword", Material.IRON_SWORD),
	IRON_SHOVEL("ironshovel", 256, "Iron Shovel", Material.IRON_SPADE),
	IRON_PICKAXE("ironpickaxe", 257, "Iron Pickaxe", Material.IRON_PICKAXE),
	IRON_AXE("ironaxe", 258, "Iron Axe", Material.IRON_AXE),
	IRON_HOE("ironhoe", 292, "Iron Hoe", Material.IRON_HOE),
	DIAMOND_SWORD("diamondsword", 276, "Diamond Sword", Material.DIAMOND_SWORD),
	DIAMOND_SHOVEL("diamondshovel", 277, "Diamond Shovel",
			Material.DIAMOND_SPADE),
	DIAMOND_PICKAXE("diamondpickaxe", 278, "Diamond Pickaxe",
			Material.DIAMOND_PICKAXE),
	DIAMOND_AXE("diamondaxe", 279, "Diamond Axe", Material.DIAMOND_AXE),
	DIAMOND_HOE("diamondhoe", 293, "Diamond Hoe", Material.DIAMOND_HOE),
	GOLD_SWORD("goldsword", 283, "Golden Sword", Material.GOLD_SWORD),
	GOLD_SHOVEL("goldshovel", 284, "Golden Shovel", Material.GOLD_SPADE),
	GOLD_PICKAXE("goldpickaxe", 285, "Golden Pickaxe", Material.GOLD_PICKAXE),
	GOLD_AXE("goldaxe", 286, "Golden Axe", Material.GOLD_AXE),
	GOLD_HOE("goldhoe", 294, "Golden Hoe", Material.GOLD_HOE),
	SHEARS("shears", 359, "Shears", Material.SHEARS),
	FISHING_ROD("fishingrod", 346, "Fishing Rod", Material.FISHING_ROD),
	BOW("bow", 261, "Bow", Material.BOW);

	private final String name;
	private final int id;
	private final String realName;
	private final Material material;

	Tools(String name, int id, String realName, Material material) {
		this.name = name;
		this.id = id;
		this.realName = realName;
		this.material = material;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRealName() {
		return realName;
	}

	public static Tools getTool(int id) {
		if (tools.containsKey(id)) {
			return tools.get(id);
		} else {
			return null;
		}
	}

	public static Tools getTool(String name) {
		if (names.containsKey(name)) {
			return names.get(name);
		} else {
			return null;
		}
	}

	public static boolean isTool(int id) {
		return tools.containsKey(id);
	}

	public static boolean isTool(Material mat) {
		return materials.containsValue(mat);
	}

	private static Map<Integer, Tools> tools = new HashMap<Integer, Tools>();
	private static Map<String, Tools> names = new HashMap<String, Tools>();
	private static Map<Tools, String> realNames = new HashMap<Tools, String>();
	private static Map<Tools, Material> materials = new HashMap<Tools, Material>();

	static {
		for (Tools tool : EnumSet.allOf(Tools.class)) {
			tools.put(tool.id, tool);
			names.put(tool.name, tool);
			realNames.put(tool, tool.realName);
			materials.put(tool, tool.material);
		}
	}
}
