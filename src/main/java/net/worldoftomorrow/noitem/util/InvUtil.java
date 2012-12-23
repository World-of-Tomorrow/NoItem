package net.worldoftomorrow.noitem.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvUtil {
	public static void switchItems(int s1, int s2, Inventory inv) {
		ItemStack i1 = inv.getItem(s1);
		ItemStack i2 = inv.getItem(s2);
		inv.setItem(s1, i2);
		inv.setItem(s2, i1);
	}
}
