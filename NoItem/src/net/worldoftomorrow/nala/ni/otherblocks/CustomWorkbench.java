package net.worldoftomorrow.nala.ni.otherblocks;

public class CustomWorkbench extends CustomBlock {

    private short resultSlot;
    private boolean hasResultSlot;

    public CustomWorkbench(int id, short data, CustomType type,
            short resultSlot, String name) {
        super(id, data, type, name);
        this.resultSlot = resultSlot;
        this.hasResultSlot = true;
    }

    public CustomWorkbench(int id, short data, CustomType type, String name) {
        super(id, data, type, name);
        this.hasResultSlot = false;
        this.resultSlot = -999;
    }

    public boolean hasResultSlot() {
        return this.hasResultSlot;
    }

    public short getResultSlot() {
        return this.resultSlot;
    }

    public boolean isResultSlot(short slot) {
        return slot == this.resultSlot;
    }

}
