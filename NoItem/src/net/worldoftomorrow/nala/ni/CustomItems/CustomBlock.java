package net.worldoftomorrow.nala.ni.CustomItems;

public abstract class CustomBlock {

	final CustomType type;
	final int id;
	final short data;
	final String name;

	/**
	 * Construct a new custom block object
	 * @param id
	 * @param data
	 * @param type
	 * @param name
	 */
	public CustomBlock(int id, short data, CustomType type, String name) {
		this.id = id;
		this.data = data;
		this.type = type;
		this.name = name;
	}

	/**
	 * Get the blocks {@link CustomType}
	 * @return {@link CustomType}
	 */
	public CustomType getType() {
		return this.type;
	}

	/**
	 * Get the blocks ID
	 * @return int
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Get the blocks Data
	 * @return short
	 */
	public short getData() {
		return this.data;
	}

	/**
	 * Get the blocks name
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
}
