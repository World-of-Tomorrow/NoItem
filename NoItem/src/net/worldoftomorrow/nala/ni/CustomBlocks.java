package net.worldoftomorrow.nala.ni;

import java.util.ArrayList;
import java.util.List;

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
}
