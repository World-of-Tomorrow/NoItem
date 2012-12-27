package net.worldoftomorrow.noitem.lists;

import org.bukkit.inventory.ItemStack;

public class Tools extends YamlFile {

	public Tools() {
		super("lists", "tools.yml");
	}
	
	public boolean isTool(ItemStack item) {
		return isTool(item.getTypeId());
	}
	
	public boolean isTool(int id) {
		return isPickaxe(id) || isAxe(id) || isSword(id) || isShovel(id) || isHoe(id) || isShear(id) || isOther(id);
	}
	
	public boolean isPickaxe(ItemStack item) {
		return isPickaxe(item.getTypeId());
	}
	
	public boolean isPickaxe(int id) {
		return this.getConfig().getList("Pickaxes").contains(id);
	}
	
	public boolean isAxe(ItemStack item) {
		return isAxe(item.getTypeId());
	}
	
	public boolean isAxe(int id) {
		return this.getConfig().getList("Axes").contains(id);
	}
	
	public boolean isSword(ItemStack item) {
		return isSword(item.getTypeId());
	}
	
	public boolean isSword(int id) {
		return this.getConfig().getList("Swords").contains(id);
	}
	
	public boolean isShovel(ItemStack item) {
		return isShovel(item.getTypeId());
	}
	
	public boolean isShovel(int id) {
		return this.getConfig().getList("Shovels").contains(id);
	}
	
	public boolean isHoe(ItemStack item) {
		return isHoe(item.getTypeId());
	}
	
	public boolean isHoe(int id) {
		return this.getConfig().getList("Hoes").contains(id);
	}
	
	public boolean isShear(ItemStack item) {
		return isShear(item.getTypeId());
	}
	
	public boolean isShear(int id) {
		return this.getConfig().getList("Shears").contains(id);
	}
	
	public boolean isOther(ItemStack item) {
		return isOther(item.getTypeId());
	}
	
	public boolean isOther(int id) {
		return this.getConfig().getList("Other").contains(id);
	}
}
