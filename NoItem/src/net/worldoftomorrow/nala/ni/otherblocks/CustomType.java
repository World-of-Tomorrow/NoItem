package net.worldoftomorrow.nala.ni.otherblocks;

public enum CustomType {
    FURNACE("furnace", "oven"), WORKBENCH("crafting_table", "craftingtable",
            "workbench");

    private String[] names;

    private CustomType(String... names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }
}
