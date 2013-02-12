package net.worldoftomorrow.noitem.util;

import net.worldoftomorrow.noitem.NoItem;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Util {
	public static void switchItems(int s1, int s2, Inventory inv) {
		ItemStack i1 = inv.getItem(s1);
		ItemStack i2 = inv.getItem(s2);
		inv.setItem(s1, i2);
		inv.setItem(s2, i1);
	}
	
	public static boolean playerHasPerm(Player p, String perm) {
		return NoItem.getPermsManager().has(p, perm);
	}
	
	public static boolean playerHasPerm(Player p, String perm, ItemStack item) {
		return NoItem.getPermsManager().has(p, perm, item);
	}
	
	public static boolean playerHasPerm(Player p, String perm, Block b) {
		return NoItem.getPermsManager().has(p, perm, b);
	}
	
	public static boolean playerHasPerm(Player p, String perm, Entity e) {
		return NoItem.getPermsManager().has(p, perm, e);
	}
	
	public static boolean playerHasPerm(Player p, int data) {
		return NoItem.getPermsManager().has(p, data);
	}
}
