package net.worldoftomorrow.nala.ni.otherblocks;

import java.util.ArrayList;
import java.util.List;

public class CustomWorkbench extends CustomBlock {

    private final List<Short> resultSlots;
    private final boolean hasResultSlot;

    public CustomWorkbench(int id, short data, CustomType type,
            List<Short> resultSlots, String name) {
        super(id, data, type, name);
        this.resultSlots = resultSlots;
        this.hasResultSlot = true;
    }

    public CustomWorkbench(int id, short data, CustomType type, String name) {
        super(id, data, type, name);
        this.hasResultSlot = false;
        this.resultSlots = new ArrayList<Short>();
    }

    public boolean hasResultSlot() {
        return this.hasResultSlot;
    }

    public List<Short> getResultSlots() {
        return this.resultSlots;
    }

    public boolean isResultSlot(short slot) {
        for (short s : this.resultSlots) {
            if (s == slot) {
                return true;
            }
        }
        return false;
    }

}
