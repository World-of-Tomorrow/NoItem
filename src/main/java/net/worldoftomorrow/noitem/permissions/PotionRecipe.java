package net.worldoftomorrow.noitem.permissions;

import org.bukkit.inventory.ItemStack;

public class PotionRecipe {
	private final short potiondata;
	private final int ingredient;
	
	public PotionRecipe(short data, ItemStack ing) {
		this.potiondata = data;
		this.ingredient = ing.getTypeId();
	}
	
	public PotionRecipe(short data, int ing) {
		this.potiondata = data;
		this.ingredient = ing;
	}
	
	public short getData() {
		return this.potiondata;
	}
	
	public int getIngId() {
		return this.ingredient;
	}
	
	public String getRecipe() {
		return this.potiondata + ":" + this.ingredient;
	}
}
