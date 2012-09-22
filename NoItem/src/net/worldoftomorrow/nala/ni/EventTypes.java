package net.worldoftomorrow.nala.ni;

public enum EventTypes {
	CRAFT("craft", Config.getBoolean("Notify.NoCraft"), Config.getString("Messages.NoCraft")),
	BREW("brew", Config.getBoolean("Notify.NoBrew"), Config.getString("Messages.NoBrew")),
	WEAR("wear", Config.getBoolean("Notify.NoWear"), Config.getString("Messages.NoWear")),
	PICKUP("pick up", Config.getBoolean("Notify.NoPickup"), Config.getString("Messages.NoPickup")),
	DROP("drop", Config.getBoolean("Notify.NoDrop"), Config.getString("Messages.NoDrop")),
	USE("use", Config.getBoolean("Notify.NoUse"), Config.getString("Messages.NoUse")),
	HOLD("hold", Config.getBoolean("Notify.NoHold"), Config.getString("Messages.NoHold")),
	SMELT("smelt", Config.getBoolean("Notify.NoCook"), Config.getString("Messages.NoCook")),
	COOK("cook", Config.getBoolean("Notify.NoCook"), Config.getString("Messages.NoCook")),
	BREAK("break", Config.getBoolean("Notify.NoBreak"), Config.getString("Messages.NoBreak")),
	PLACE("place", Config.getBoolean("Notify.NoPlace"), Config.getString("Messages.NoPlace")),
	DRINK("drink", Config.getBoolean("Notify.NoDrink"), Config.getString("Messages.NoDrink")),
	OPEN("open", Config.getBoolean("Notify.NoOpen"), Config.getString("Messages.NoOpen")),
	HAVE("have", Config.getBoolean("Notify.NoHave"), Config.getString("Messages.NoHave")),
	ENCHANT("enchant", Config.getBoolean("NoEnchant"), Config.getString("Messages.NoEnchant"));

	private final String name;
	private final boolean notify;
	private final String message;

	EventTypes(String name, Boolean notify, String msg) {
		this.name = name;
		this.notify = notify;
		this.message = msg;
	}

	public String getName() {
		return this.name;
	}

	public boolean doNotify() {
		return this.notify;
	}
	
	public String getMessage() {
		return message;
	}
}
