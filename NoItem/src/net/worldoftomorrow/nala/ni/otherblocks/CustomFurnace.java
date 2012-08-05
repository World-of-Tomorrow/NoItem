package net.worldoftomorrow.nala.ni.otherblocks;

import java.util.List;

public class CustomFurnace extends CustomBlock {

    private List<Short> resultSlots;
    private List<Short> fuelSlots;
    private List<Short> itemSlots;

    public CustomFurnace(int id, short data, CustomType type,
            List<Short> resultSlots, List<Short> fuelSlots,
            List<Short> itemSlots, String name) {
        super(id, data, type, name);
        this.resultSlots = resultSlots;
        this.fuelSlots = fuelSlots;
        this.itemSlots = itemSlots;
    }

    public List<Short> getResultSlots() {
        return this.resultSlots;
    }

    public List<Short> getFuelSlots() {
        return this.fuelSlots;
    }

    public List<Short> getItemSlots() {
        return this.itemSlots;
    }

    public boolean isResultSlot(short slot) {
        for (short s : this.resultSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

    public boolean isFuelSlot(short slot) {
        for (short s : this.fuelSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

    public boolean isItemSlot(short slot) {
        for (short s : this.itemSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

}
