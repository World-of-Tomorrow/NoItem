package net.worldoftomorrow.nala.ni;

import org.bukkit.entity.Player;

public enum Permissions {
	ADMIN("noitem.admin"), ALLITEMS("noitem.allitems"), NOCRAFT(
			"noitem.nocraft."), NOPICKUP("noitem.nopickup."), NOBREW(
			"noitem.nobrew.");

	private String perm;

	private Permissions(String perm) {
		this.perm = perm;
	}

	public boolean has(Player p) {
		return p.hasPermission(this.perm);
	}

	public String getPerm() {
		return this.perm;
	}

	public boolean has(Player p, int iid, int dv) {
		if (this.perm == NOCRAFT.getPerm() || this.perm == NOPICKUP.getPerm()) {
			if (dv != 0) {
				String perm = this.perm
						.concat(Integer.toString(iid))
						.concat(".")
						.concat(Integer.toString(dv));
				return p.hasPermission(perm); // If data value != 0
			} else {
				return p.hasPermission(this.perm.concat(Integer.toString(iid)));
			}
		} else {
			return p.hasPermission(this.perm);
		}
	}

	public boolean has(Player p, int iid) {
		if (perm == NOCRAFT.getPerm() || perm == NOPICKUP.getPerm()) {
			return p.hasPermission(this.perm + iid);
		}
		return p.hasPermission(this.perm); // If the permission does not need an
											// item id.
	}

	public boolean has(Player p, int potion, int ingredient,
			boolean letsMakeMeDifferent) {
		if (perm == NOBREW.getPerm()) {
			return p.hasPermission(this.perm
					.concat(Integer.toString(potion)
					.concat(".")
					.concat(Integer.toString(ingredient))));
		} else
			return false;
	}
}