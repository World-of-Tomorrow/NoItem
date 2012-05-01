package net.worldoftomorrow.nala.ni;

import org.bukkit.entity.Player;

public enum Permissions {
	ADMIN("noitem.admin"),
	ALLITEMS("noitem.allitems"),
	NOCRAFT("noitem.nocraft."),
	NOPICKUP("noitem.nopickup."),
	NOBREW("noitem.nobrew."),
	NOUSE("noitem.nouse.");

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
		}
		if (perm == NOBREW.getPerm()) {
			return p.hasPermission(this.perm
					.concat(Integer.toString(iid)
					.concat(".")
					.concat(Integer.toString(dv))));
		}
		
		else {
			return p.hasPermission(this.perm);
		}
	}

	public boolean has(Player p, int iid) {
		if (perm == NOCRAFT.getPerm() || perm == NOPICKUP.getPerm()) {
			return p.hasPermission(this.perm + iid);
		}
		if(perm == NOUSE.getPerm()){
			if(p.hasPermission(this.perm + iid)){
				return true;
			}
			if (Tools.tools.containsKey(iid)) {
				if (p.hasPermission(this.perm + Tools.getTool(iid).getName())) {
					return true;
				}
				return false;
			} else {
				return false;
			}
		} else {
			return p.hasPermission(this.perm); // If the permission does not need an
		}
											// item id.
	}
}