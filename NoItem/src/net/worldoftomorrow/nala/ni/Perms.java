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

	private boolean check(Player p, String perm, boolean exempt) {
		// Log.debug("Checking Perm: " + perm);
		final boolean value;
		if (Vault.vaultPerms) {
			value = Vault.has(p, perm);
		} else {
			value = p.hasPermission(perm);
		}
		// If we are using the permissions as a white-list, return the opposite value.
		// If we are using them as a blacklist, return the true value.
		// Or if the permission is exempt, return the true value
		return (!exempt && Config.getBoolean("PermsAsWhiteList")) ? !value : value;
	}
	
	private boolean check(Player p, String perm) {
		return this.check(p, perm, false);
	}

	public boolean has(Player p, ItemStack stack) {
		return this.has(p, stack.getType(), stack.getDurability());
	}

	public boolean has(Player p, Block b) {
		return this.has(p, b.getType(), b.getData());
	}

    // Returns true only if a permission is defined and explicitly set to false rather than not set and false by default
    public boolean checkFalse(Player p, String perm) {
        return p.isPermissionSet(perm) && !check(p, perm, true);
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

        // Have we got a name or num node explicitly defined to false as an override of a more general case?
        if (checkFalse(p, namePerm) || checkFalse(p, numPerm)) {
            return Config.getBoolean("PermsAsWhiteList");
        } else if (check(p, namePerm, true) || check(p, numPerm, true)) { // What about explicitly disallowing?
            return !Config.getBoolean("PermsAsWhiteList");
        }

        // Lets try for the more general .all case
        if (checkFalse(p, allNamePerm) || checkFalse(p, allNumPerm)) {
            return Config.getBoolean("PermsAsWhiteList");
        } else if (check(p, allNamePerm, true) || check(p, allNumPerm, true)) {
            return !Config.getBoolean("PermsAsWhiteList");
        }

        // No nodes set explicitly, lets try the wildcard node.
        return this.check(p, perm + "*");
	}

	public boolean has(Player p) {
		if (!perm.equals(ADMIN.perm) && !perm.equals(ALLITEMS.perm)) {
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		} else {
			return this.check(p, perm, true);
		}
	}

	public boolean has(Player p, String recipe) {
		if (perm.equals(NOBREW.perm)|| perm.equals(ONDEATH.perm)) {

			if (Perms.ALLITEMS.has(p) && !perm.equals(ONDEATH.perm)) return false;
			final boolean value = this.check(p, perm + "*") || this.check(p, perm + recipe);
			// If is the ondeath permission & permissions are a white-list, return the opposite value.
			return (perm.equals(ONDEATH.perm) && Config.getBoolean("PermsAsWhiteList")) ? !value : value;
		} else {
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		}
	}
	
	public boolean has(Player p, String enchantment, ItemStack item) {
		if(!perm.equals(NOENCHANT.perm))
			throw new UnsupportedOperationException("Incorrect checking of a permission.");
		if(Perms.ALLITEMS.has(p))
			return false;
		String allperm = perm + "*";
		String numperm = perm + enchantment + "." + item.getTypeId();
		String nameperm = perm + enchantment + "." + this.getItemName(item);
		return this.check(p, allperm) || this.check(p, nameperm) || this.check(p, numperm);
	}

	public boolean has(Player p, short data) {
		if (!perm.equals(NODRINK.perm)) {
			throw new UnsupportedOperationException(
					"Incorrect checking of a permission.");
		}
		if (Perms.ALLITEMS.has(p))
			return false;
		return this.check(p, perm + "*") || this.check(p, perm + data);
	}
	
	private String getItemName(ItemStack item) {
		return this.getItemName(item.getTypeId(), item.getDurability());
	}
	
	private String getItemName(int id, short data) {
		if (CustomBlocks.isCustomBlock(id, data))
			return CustomBlocks.getCustomBlock(id, data).getName();
		Material mat = Material.getMaterial(id);
		if (mat != null)
			return mat.name().replace("_", "").toLowerCase();
		return Integer.toString(id);
	}
}
