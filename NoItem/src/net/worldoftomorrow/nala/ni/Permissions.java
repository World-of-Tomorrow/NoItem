package net.worldoftomorrow.nala.ni;

import org.bukkit.entity.Player;

public enum Permissions {
	ADMIN("noitem.admin"), ALLITEMS("noitem.allitems"), PERITEMCRAFT(
			"noitem.nocraft."), PERITEMPICK("noitem.nopickup.");

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

	public boolean has(Player p, int iid) {
		if ((this.perm == PERITEMCRAFT.getPerm())
				|| (this.perm == PERITEMPICK.getPerm())) {
			return p.hasPermission(this.perm + iid);
		}
		return p.hasPermission(this.perm);
	}
}