package net.worldoftomorrow.nala.ni;

public enum EventTypes {
	CRAFT("CRAFT"),
	BREW("BREW"),
	WEAR("WEAR"),
	PICKUP("PICK UP"),
	USE("USE"),
	HOLD("HOLD"),
	SMELT("SMELT"),
	COOK("COOK");
	
	private final String name;
	
	EventTypes(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
