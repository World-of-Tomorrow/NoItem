package net.worldoftomorrow.nala.ni.Items;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public enum Armor {
	LEATHER_HELMET("leatherhelmet", 298, "Leather Helmet", Material.LEATHER_HELMET),
	CHAIN_HELMET("chainhelmet", 302, "Chain Helmet", Material.CHAINMAIL_HELMET),
	IRON_HELMET("ironhelmet", 306, "Iron Helmet", Material.IRON_HELMET),
	DIAMOND_HELMET("diamondhelmet", 310, "Diamond Helmet", Material.DIAMOND_HELMET),
	GOLD_HELMET("goldhelmet", 314, "Golden Helmet", Material.GOLD_HELMET),
	LEATHER_CHESTPLATE("leatherchest", 299, "Leather Chestplate",
			Material.LEATHER_CHESTPLATE),
	CHAIN_CHESTPLATE("chainchest", 303, "Chain Chestplate",
			Material.CHAINMAIL_HELMET),
	IRON_CHESTPLATE("ironchest", 307, "Iron Chestplate", Material.IRON_CHESTPLATE),
	DIAMOND_CHESTPLATE("diamondchest", 311, "Diamond Chestplate",
			Material.DIAMOND_CHESTPLATE),
	GOLD_CHESTPLATE("goldchest", 315, "Golden Chestplate", Material.GOLD_CHESTPLATE),
	LEATHER_PANTS("leatherpants", 300, "Leather Pants", Material.LEATHER_LEGGINGS),
	CHAIN_PANTS("chainpants", 304, "Chain Pants", Material.CHAINMAIL_LEGGINGS),
	IRON_PANTS("ironpants", 308, "Iron Pants", Material.IRON_LEGGINGS),
	DIAMOND_PANTS("diamondpants", 312, "Diamond Pants", Material.DIAMOND_LEGGINGS),
	GOLD_PANTS("goldpants", 316, "Golden Pants", Material.GOLD_LEGGINGS),
	LEATHER_BOOTS("leatherboots", 301, "Leather Boots", Material.LEATHER_BOOTS),
	CHAIN_BOOTS("chainboots", 305, "Chain Boots", Material.CHAINMAIL_BOOTS),
	IRON_BOOTS("ironboots", 309, "Iron Boots", Material.IRON_BOOTS),
	DIAMOND_BOOTS("diamondboots", 313, "Diamond Boots", Material.DIAMOND_BOOTS),
	GOLD_BOOTS("goldboots", 317, "Golden Boots", Material.GOLD_BOOTS);

	private final String name;
	private final int id;
	private final String realName;
	private final Material material;

	private Armor(String name, int id, String realName, Material material) {
		this.name = name;
		this.id = id;
		this.realName = realName;
		this.material = material;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRealName() {
		return realName;
	}

	public static Armor getArmour(int id) {
		if (armors.containsKey(id)) {
			return armors.get(id);
		} else {
			return null;
		}
	}

	public static Armor getArmour(String name) {
		if (names.containsKey(name)) {
			return names.get(name);
		} else {
			return null;
		}
	}

	public static boolean isArmor(int id) {
		return armors.containsKey(id);
	}

	public static boolean isArmor(Material mat) {
		return materials.containsValue(mat);
	}

	private static Map<Integer, Armor> armors = new HashMap<Integer, Armor>();
	private static Map<String, Armor> names = new HashMap<String, Armor>();
	private static Map<Armor, String> realNames = new HashMap<Armor, String>();
	private static Map<Armor, Material> materials = new HashMap<Armor, Material>();

	static {
		for (Armor armor : EnumSet.allOf(Armor.class)) {
			armors.put(armor.id, armor);
			names.put(armor.name, armor);
			realNames.put(armor, armor.realName);
			materials.put(armor, armor.material);
		}
	}
}
