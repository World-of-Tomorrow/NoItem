package net.worldoftomorrow.nala.ni.Items;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TekkitTools {
    RM_PICK("rmpickaxe", 27564, "RedMatter Pickaxe"), RM_SHOVEL("rmshovel",
            27565, "RedMatter Shovel"), RM_HOE("rmhoe", 27566, "RedMatter How"), RM_SWORD(
            "rmsword", 27567, "RedMatter Sword"), RM_AXE("rmaxe", 27568,
            "RedMatter Axe"), RM_SHEARS("rmshears", 27569, "RedMatter Shears"), RM_HAMMER(
            "rmhammer", 27570, "RedMatter Hammer"), RM_KATAR("rmkatar", 27572,
            "RedMatter Katar"), RM_MORNINGSTAR("rmmorningstar", 27573,
            "RedMatter Morning Star");

    private final String name;
    private final int id;
    private final String realName;

    private TekkitTools(String name, int id, String realName) {
        this.name = name;
        this.id = id;
        this.realName = realName;
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

    public static TekkitTools getTool(int id) {
        if (tekkitTools.containsKey(id)) {
            return tekkitTools.get(id);
        } else {
            return null;
        }
    }

    public static boolean isTekkitTool(int id) {
        return tekkitTools.containsKey(id);
    }

    private static Map<Integer, TekkitTools> tekkitTools = new HashMap<Integer, TekkitTools>();
    private static Map<String, TekkitTools> names = new HashMap<String, TekkitTools>();
    private static Map<TekkitTools, String> realNames = new HashMap<TekkitTools, String>();

    static {
        for (TekkitTools tool : EnumSet.allOf(TekkitTools.class)) {
            tekkitTools.put(tool.id, tool);
            names.put(tool.name, tool);
            realNames.put(tool, tool.realName);
        }
    }
}
