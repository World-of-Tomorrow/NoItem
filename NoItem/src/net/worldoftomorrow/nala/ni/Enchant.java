package net.worldoftomorrow.nala.ni;

import org.bukkit.enchantments.EnchantmentWrapper;

public enum Enchant {
	PROTECTION(new EnchantmentWrapper(0), 0),
	FIRE_PROTECTION(new EnchantmentWrapper(1), 1),
	FEATHER_FALLING(new EnchantmentWrapper(2), 2),
	BLAST_PROTECTION(new EnchantmentWrapper(3), 3),
	PROJECTILE_PROTECTION(new EnchantmentWrapper(4), 4),
	RESPIRATION(new EnchantmentWrapper(5), 5),
	AQUA_AFFINITY(new EnchantmentWrapper(6), 6),
	SHARPNESS(new EnchantmentWrapper(16), 16),
	SMITE(new EnchantmentWrapper(17), 17),
	BANE_OF_ARTHROPODS(new EnchantmentWrapper(18), 18),
	KNOCKBACK(new EnchantmentWrapper(19), 19),
	FIRE_ASPECT(new EnchantmentWrapper(20), 20),
	LOOTING(new EnchantmentWrapper(21), 21),
	EFFICIENCY(new EnchantmentWrapper(32), 32),
	SILK_TOUCH(new EnchantmentWrapper(33), 33),
	UNBREAKING(new EnchantmentWrapper(34), 34),
	FORTUNE(new EnchantmentWrapper(35), 35),
	POWER(new EnchantmentWrapper(48), 48),
	PUNCH(new EnchantmentWrapper(49), 49),
	FLAME(new EnchantmentWrapper(50), 50),
	INFINITY(new EnchantmentWrapper(51), 51);
	
	private final EnchantmentWrapper enchantment;
	private final int id;
	
	private Enchant (EnchantmentWrapper enchantment, int id) {
		this.enchantment = enchantment;
		this.id = id;
	}
	
	public EnchantmentWrapper getEnchantment() {
		return this.enchantment;
	}
	
	public String getName() {
		return this.name().toLowerCase().replace("_", "");
	}
	
	public int getID() {
		return this.id;
	}
	
	public static Enchant getByID(int id) {
		for(Enchant enchant : Enchant.values()) {
			if(enchant.getID() == id) {
				return enchant;
			}
		}
		return null;
	}
}
