package net.worldoftomorrow.nala.ni;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum Perms {
	ADMIN("noitem.admin"),
	ALLITEMS("noitem.allitems"),
	NOCRAFT("noitem.nocraft."),
	NOPICKUP("noitem.nopickup."),
	NODROP("noitem.nodrop."),
	NOBREW("noitem.nobrew."),
	NOUSE("noitem.nouse."),
	NOHOLD("noitem.nohold."),
	NOWEAR("noitem.nowear."),
	NOCOOK("noitem.nocook."),
	NOPLACE("noitem.noplace."),
	NOBREAK("noitem.nobreak."),
	ONDEATH("noitem.ondeath."),
	NODRINK("noitem.nodrink."),
	NOOPEN("noitem.noopen."),
	NOHAVE("noitem.nohave."),
	NOENCHANT("noitem.noenchant.");

	private final String perm;

	private Perms(String perm) {
		this.perm = perm;
	}

	private boolean check(Player p, String perm) {
		// Log.debug("Checking Perm: " + perm);
		if (Vault.vaultPerms) {
			return Vault.has(p, perm);
		} else {
			return p.hasPermission(perm);
		}
	}

	public boolean has(Player p, ItemStack stack) {
		return this.has(p, stack.getType(), stack.getDurability());
	}

	public boolean has(Player p, Block b) {
		return this.has(p, b.getType(), b.getData());
	}

	public boolean has(Player p, Material mat, short data) {
		if (perm.equals(ADMIN.perm)
				|| perm.equals(ALLITEMS.perm)
				|| perm.equals(NOBREW.perm)
				|| perm.equals(ONDEATH.perm)) {
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		}
		if (Perms.ALLITEMS.has(p)) {
			return false;
		}
		String namePerm;
		String numPerm;
		String allNamePerm = perm + this.getItemName(mat.getId(), data) + ".all";
		String allNumPerm = perm + mat.getId() + ".all";
		if (data > 0) {
			namePerm = perm + this.getItemName(mat.getId(), data) + "." + data;
			numPerm = perm + mat.getId() + "." + data;
		} else {
			namePerm = perm + this.getItemName(mat.getId(), data);
			numPerm = perm + mat.getId();
		}
		return this.check(p, namePerm)
				|| this.check(p, numPerm)
				|| this.check(p, allNamePerm)
				|| this.check(p, allNumPerm);
	}

	public boolean has(Player p) {
		if (!perm.equals(ADMIN.perm)
				&& !perm.equals(ALLITEMS.perm)) {
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		} else {
			return this.check(p, perm);
		}
	}

	public boolean has(Player p, String recipe) {
		if (perm.equals(NOBREW.perm)
				|| perm.equals(ONDEATH.perm)) {
			if (Perms.ALLITEMS.has(p)) {
				return false;
			}
			return this.check(p, perm + recipe);
		} else {
			throw new UnsupportedOperationException(
					"Incorrect checking of a permission.");
		}
	}
	
	public boolean has(Player p, String enchantment, ItemStack item) {
		if(!perm.equals(NOENCHANT.perm))
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		if(Perms.ALLITEMS.has(p))
			return false;
		String numperm = perm + enchantment + "." + item.getTypeId();
		String nameperm = perm + enchantment + "." + this.getItemName(item);
		return this.check(p, nameperm) || this.check(p, numperm);
	}

	public boolean has(Player p, short data) {
		if (!perm.equals(NODRINK.perm)) {
			throw new UnsupportedOperationException(
					"Incorrect checking of a permission.");
		}
		if (Perms.ALLITEMS.has(p))
			return false;
		return this.check(p, perm + data);
	}
	
	private String getItemName(ItemStack item) {
		return this.getItemName(item.getTypeId(), item.getDurability());
	}
	
	private String getItemName(int id, short data) {
		if (CustomBlocks.isCustomBlock(id, data))
			return CustomBlocks.getCustomBlock(id, data).getName();
		if (Material.getMaterial(id) != null)
			return Material.getMaterial(id).name().replace("_", "").toLowerCase();
		return Integer.toString(id);
	}
}
