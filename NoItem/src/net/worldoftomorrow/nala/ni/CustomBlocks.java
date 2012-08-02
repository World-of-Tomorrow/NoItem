package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

import net.worldoftomorrow.nala.ni.otherblocks.CustomBlock;
import net.worldoftomorrow.nala.ni.otherblocks.CustomFurnace;
import net.worldoftomorrow.nala.ni.otherblocks.CustomWorkbench;

public class CustomBlocks {

	private static List<CustomFurnace> furnaces;
	private static List<CustomWorkbench> workbenches;

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

	public static boolean isFurnace(int id, short data) {
		for (CustomFurnace f : furnaces) {
			if (id == f.getID() && data == f.getData()) {
				return true;
			}
		}
		return false;
	}

	public static CustomWorkbench getWorkbench(int id, short data) {
		for (CustomWorkbench w : workbenches) {
			if (id == w.getID() && data == w.getData()) {
				return w;
			}
		}
		return null;
	}

	public static CustomFurnace getFurnace(int id, short data) {
		for (CustomFurnace f : furnaces) {
			if (id == f.getID() && data == f.getData()) {
				return f;
			}
		}
		return null;
	}
	
	public static boolean isCustomBlock(Block b) {
		return isCustomBlock(b.getTypeId());
	}
	
	public static boolean isCustomBlock(int id) {
		if(furnaces.isEmpty()) return false;
		for(CustomBlock block : furnaces) {
			if(block.getID() == id){
				return true;
			}
		}
		if(workbenches.isEmpty()) return false;
		for(CustomBlock block : workbenches) {
			if(block.getID() == id) {
				return true;
			}
		}
		return false;
	}
}
