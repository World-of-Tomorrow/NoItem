package net.worldoftomorrow.nala.ni.CustomItems;

import java.util.List;

public class CustomWorkbench extends CustomBlock {

	private final List<Short> resultSlots;
	private final List<Short> recipeSlots;
	private final boolean hasResultSlot;
	private final boolean fakeRecipeItems;
	private final boolean isNotBlock;

	public CustomWorkbench(int id, short data, CustomType type,
			List<Short> resultSlots, List<Short> recipeSlots, String name,
			boolean fakeRecipeItems, boolean isNotBlock) {
		super(id, data, type, name);
		this.fakeRecipeItems = fakeRecipeItems;
		this.resultSlots = resultSlots;
		this.recipeSlots = recipeSlots;
		this.hasResultSlot = true;
		this.isNotBlock = isNotBlock;
	}

	public boolean hasResultSlot() {
		return this.hasResultSlot;
	}

	public boolean usesFakeRecipeItems() {
		return this.fakeRecipeItems;
	}

	public List<Short> getResultSlots() {
		return this.resultSlots;
	}

	public List<Short> getRecipeSlots() {
		return this.recipeSlots;
	}

	public boolean isResultSlot(short slot) {
		for (short s : this.resultSlots) {
			if (s == slot) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBlock() {
		return !isNotBlock;
	}

	public boolean isRecipeSlot(short slot) {
		for (short s : this.recipeSlots) {
			if (s == slot) {
				return true;
			}
		}
		return false;
	}

}
