package net.worldoftomorrow.noitem.lists;

import org.bukkit.inventory.ItemStack;

public class Armor extends YamlFile {

	public Armor() {
		super("lists", "armor.yml");
	}
	
	public boolean isArmor(ItemStack item) {
		return isArmor(item.getTypeId());
	}
	
	public boolean isArmor(int id) {
		return isHelmet(id) || isChestplate(id) || isLeggings(id) || isBoots(id);
	}
	
	public boolean isHelmet(ItemStack item) {
		return isHelmet(item.getTypeId());
	}
	
	public boolean isHelmet(int id) {
		return this.getConfig().getList("Helmets").contains(id);
	}
	
	public boolean isChestplate(ItemStack item) {
		return isChestplate(item.getTypeId());
	}
	
	public boolean isChestplate(int id) {
		return this.getConfig().getList("Chestplates").contains(id);
	}
	
	public boolean isLeggings(ItemStack item) {
		return isLeggings(item.getTypeId());
	}
	
	public boolean isLeggings(int id) {
		return this.getConfig().getList("Leggings").contains(id);
	}
	
	public boolean isBoots(ItemStack item) {
		return this.getConfig().getList("Boots").contains(item.getTypeId());
	}
	
	public boolean isBoots(int id) {
		return this.getConfig().getList("Boots").contains(id);
	}

}
