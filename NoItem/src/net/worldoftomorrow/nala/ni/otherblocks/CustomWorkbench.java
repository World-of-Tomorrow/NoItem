package net.worldoftomorrow.nala.ni.otherblocks;

import java.util.List;

public class CustomWorkbench extends CustomBlock {

    private final List<Short> resultSlots;
    private final List<Short> recipeSlots;
    private final boolean hasResultSlot;

    public CustomWorkbench(int id, short data, CustomType type,
            List<Short> resultSlots, List<Short> recipeSlots, String name) {
        super(id, data, type, name);
        this.resultSlots = resultSlots;
        this.recipeSlots = recipeSlots;
        this.hasResultSlot = true;
    }

    public boolean hasResultSlot() {
        return this.hasResultSlot;
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
    
    public boolean isRecipeSlot(short slot) {
        for (short s : this.recipeSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

}
