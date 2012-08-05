package net.worldoftomorrow.nala.ni.otherblocks;

public abstract class CustomBlock {

    final CustomType type;
    final int id;
    final short data;
    final String name;

    public CustomBlock(int id, short data, CustomType type, String name) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.name = name;
    }

    public CustomType getType() {
        return this.type;
    }

    public int getID() {
        return this.id;
    }

    public short getData() {
        return this.data;
    }

    public String getName() {
        return this.name;
    }
}
