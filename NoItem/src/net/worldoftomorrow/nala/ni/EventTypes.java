package net.worldoftomorrow.nala.ni;

public enum EventTypes {
    CRAFT("craft", Config.notifyNoCraft()), BREW("brew", Config.notifyNoBrew()), WEAR(
            "wear", Config.notfiyNoWear()), PICKUP("pick up", Config
            .notifyNoPickup()), DROP("drop", Config.notifyNoDrop()), USE("use",
            Config.notifyNoUse()), HOLD("hold", Config.notifyNoHold()), SMELT(
            "smelt", Config.notifyNoCook()), COOK("cook", Config.notifyNoCook()), BREAK(
            "break", Config.notifyNoBreak()), // TODO: Make this configurable
    PLACE("place", Config.notifyNoPlace());

    private final String name;
    private final boolean notify;

    EventTypes(String name, Boolean notify) {
        this.name = name;
        this.notify = notify;
    }

    public String getName() {
        return this.name;
    }

    public boolean doNotify() {
        return this.notify;
    }
}
