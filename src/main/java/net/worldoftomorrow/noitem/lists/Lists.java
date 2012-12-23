package net.worldoftomorrow.noitem.lists;

import org.bukkit.inventory.ItemStack;

public class Lists {
	
	private final Armor armor;
	private final Tools tools;
	
	public Lists() {
		armor = new Armor();
		tools = new Tools();
	}
	
	public Armor getArmors() {
		return armor;
	}
	
	public Tools getTools() {
		return tools;
	}
	
	public boolean isTool(int id) {
		return tools.isTool(id);
	}
	
	public boolean isTool(ItemStack stack) {
		return tools.isTool(stack);
	}
	
	public boolean isArmor(int id) {
		return armor.isArmor(id);
	}
	
	public boolean isArmor(ItemStack stack) {
		return armor.isArmor(stack);
	}
}
