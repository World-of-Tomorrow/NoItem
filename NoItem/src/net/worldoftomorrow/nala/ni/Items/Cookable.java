package net.worldoftomorrow.nala.ni.Items;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

public enum Cookable {
    CHICKEN("chicken", 365, "Chicken", true, Material.RAW_CHICKEN), BEEF(
            "beef", 363, "Beef", true, Material.RAW_BEEF), PORK("pork", 319,
            "Porkchop", true, Material.PORK), FISH("fish", 349, "Fish", true,
            Material.RAW_FISH), LOG("log", 17, "Log", false, Material.LOG), IRON_ORE(
            "ironore", 15, "Iron Ore", false, Material.IRON_ORE), GOLD_ORE(
            "goldore", 14, "Gold Ore", false, Material.GOLD_ORE), COBBLESTONE(
            "cobblestone", 4, "Cobblestone", false, Material.COBBLESTONE), SAND(
            "sand", 12, "Sand", false, Material.SAND), CLAY("clay", 337,
            "Clay Ball", false, Material.CLAY_BALL), CACTUS("cactus", 81,
            "Cactus", false, Material.CACTUS);

    private final String name;
    private final int id;
    private final String realName;
    private final boolean isFood;
    private final Material material;

    Cookable(String name, int id, String realName, boolean isFood,
            Material material) {
        this.name = name;
        this.id = id;
        this.realName = realName;
        this.isFood = isFood;
        this.material = material;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRealName() {
        return this.realName;
    }

    public boolean isFood() {
        return this.isFood;
    }

    public static Cookable getItem(int id) {
        if (items.containsKey(id)) {
            return items.get(id);
        } else {
            return null;
        }
    }

    public static Cookable getItem(String name) {
        if (names.containsKey(name)) {
            return names.get(name);
        } else {
            return null;
        }
    }

    public static boolean isCookable(int id) {
        return items.containsKey(id);
    }

    public static boolean isCookable(Material mat) {
        return materials.containsValue(mat);
    }

    private static Map<Integer, Cookable> items = new HashMap<Integer, Cookable>();
    private static Map<String, Cookable> names = new HashMap<String, Cookable>();
    private static Map<Cookable, String> realNames = new HashMap<Cookable, String>();
    private static Map<Cookable, Material> materials = new HashMap<Cookable, Material>();

    static {
        for (Cookable item : EnumSet.allOf(Cookable.class)) {
            items.put(item.id, item);
            names.put(item.name, item);
            realNames.put(item, item.realName);
            materials.put(item, item.material);
        }
    }
}
