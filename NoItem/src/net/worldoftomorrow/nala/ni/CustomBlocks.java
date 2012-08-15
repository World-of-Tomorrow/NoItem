package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

import net.worldoftomorrow.nala.ni.otherblocks.CustomBlock;
import net.worldoftomorrow.nala.ni.otherblocks.CustomFurnace;
import net.worldoftomorrow.nala.ni.otherblocks.CustomWorkbench;

public class CustomBlocks {

    private static List<CustomFurnace> furnaces = new ArrayList<CustomFurnace>();
    private static List<CustomWorkbench> workbenches = new ArrayList<CustomWorkbench>();

    public static void setFurnaces(ArrayList<CustomFurnace> furnaces) {
        CustomBlocks.furnaces = furnaces;
    }

    public static void setWorkbenches(ArrayList<CustomWorkbench> workbenches) {
        CustomBlocks.workbenches = workbenches;
    }

    public static boolean isWorkbench(int id, short data) {
        for (CustomWorkbench w : workbenches) {
            if (id == w.getID() && data == w.getData()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isWorkbench(Block b) {
    	return isWorkbench(b.getTypeId(), b.getData());
    }

    public static boolean isFurnace(int id, short data) {
        for (CustomFurnace f : furnaces) {
            if (id == f.getID() && data == f.getData()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isFurnace(Block b) {
    	return isFurnace(b.getTypeId(), b.getData());
    }

    public static CustomWorkbench getWorkbench(int id, short data) {
        for (CustomWorkbench w : workbenches) {
            if (id == w.getID() && data == w.getData()) {
                return w;
            }
        }
        return null;
    }
    
    public static CustomWorkbench getWorkbench(Block b) {
    	return getWorkbench(b.getTypeId(), b.getData());
    }

    public static CustomFurnace getFurnace(int id, short data) {
        for (CustomFurnace f : furnaces) {
            if (id == f.getID() && data == f.getData()) {
                return f;
            }
        }
        return null;
    }
    
    public static CustomFurnace getFurnace(Block b) {
    	return getFurnace(b.getTypeId(), b.getData());
    }

    public static boolean isCustomBlock(Block b) {
        return isCustomBlock(b.getTypeId(), b.getData());
    }

    public static boolean isCustomBlock(int id, short data) {
        if (!furnaces.isEmpty()) {
            for (CustomBlock block : furnaces) {
                if (block.getID() == id && block.getData() == data) {
                    return true;
                }
            }
        }
        if (!workbenches.isEmpty()) {
            for (CustomBlock block : workbenches) {
                if (block.getID() == id && block.getData() == data) {
                    return true;
                }
            }
        }
        return false;
    }

    public static CustomBlock getCustomBlock(int id, short data) {
        if (CustomBlocks.getFurnace(id, data) != null) {
            return getFurnace(id, data);
        }

        if (CustomBlocks.getWorkbench(id, data) != null) {
            return getWorkbench(id, data);
        }
        return null;
    }
    
    public static CustomBlock getCustomBlock(Block b) {
    	return getCustomBlock(b.getTypeId(), b.getData());
    }
}
