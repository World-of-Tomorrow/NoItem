package net.worldoftomorrow.nala.ni;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;

public class VaultPerms {
	
	private Log log = new Log();
	private NoItem plugin;
	protected static boolean useVault = false;
	public VaultPerms(NoItem plugin){
		this.plugin = plugin;
		useVault = this.setupPerms();
	}
	
	public static Permission permission = null;
	
	private boolean setupPerms(){
		try{
			RegisteredServiceProvider<Permission> permProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
			if(permProvider != null){
				permission = permProvider.getProvider();
				log.log("[NoItem] Hooked into vault for permissions.");
			}
		} catch (NoClassDefFoundError e){
			log.log("[NoItem] Vault not found, using superPerms.");
		}
		return permission != null;
	}
	
	public enum Permissions {
		ADMIN("noitem.admin"),
		ALLITEMS("noitem.allitems"),
		NOCRAFT("noitem.nocraft."),
		NOPICKUP("noitem.nopickup."),
		NOBREW("noitem.nobrew."),
		NOUSE("noitem.nouse."),
		NOHOLD("noitem.nohold."),
		NOWEAR("noitem.nowear.");

		private String perm;

		private Permissions(String perm) {
			this.perm = perm;
		}

		public boolean has(Player p) {
			if(useVault){
				return permission.playerHas(p, this.perm);
			} else {
				return p.hasPermission(this.perm);
			}
		}

		public String getPerm() {
			return this.perm;
		}
		
		//TODO: Clean this up, it can be done better
		public boolean has(Player p, int iid, int dv) {
			if (perm.equalsIgnoreCase(NOCRAFT.getPerm()) || perm.equalsIgnoreCase(NOPICKUP.getPerm())) {
				if (dv != 0) {
					String perm = this.perm
							.concat(Integer.toString(iid))
							.concat(".")
							.concat(Integer.toString(dv));
					if(useVault){
						return permission.playerHas(p, perm);
					} else {
						return p.hasPermission(perm);
					} // If data value != 0
				} else {
					if(useVault){
						return permission.playerHas(p, this.perm.concat(Integer.toString(iid)));
					} else {
						return p.hasPermission(this.perm.concat(Integer.toString(iid)));
					}
				}
			}
			if (perm.equalsIgnoreCase(NOBREW.getPerm())) {
				String perm = this.perm
						.concat(Integer.toString(iid)
						.concat(".")
						.concat(Integer.toString(dv)));
				if(useVault){
					return permission.playerHas(p, perm);
				} else {
					return p.hasPermission(perm);
				}
			}
			
			else {
				if(useVault){
					return permission.playerHas(p, this.perm);
				} else {
					return p.hasPermission(this.perm);
				}
			}
		}

		public boolean has(Player p, int iid) {
			if (perm.equalsIgnoreCase(NOCRAFT.getPerm()) || perm.equalsIgnoreCase(NOPICKUP.getPerm())) {
				if(useVault){
					return permission.playerHas(p, this.perm + iid);
				} else {
					return p.hasPermission(this.perm + iid);
				}
			}
			if(perm.equalsIgnoreCase(NOUSE.getPerm()) || perm.equalsIgnoreCase(NOHOLD.getPerm())){
				if(useVault){
					//This is to check for tools aliases
					if(permission.playerHas(p, this.perm + iid)){
						return true;
					}
					if (Tools.tools.containsKey(iid)) {
						return permission.playerHas(p, this.perm + Tools.getTool(iid).getName());
					} else {
						return false;
					}
				} else {
					if(p.hasPermission(this.perm + iid)){
						return true;
					}
					if (Tools.tools.containsKey(iid)) {
						return p.hasPermission(this.perm + Tools.getTool(iid).getName());
					} else {
						return false;
					}
				}
				
			}
			if(perm.equalsIgnoreCase(NOWEAR.getPerm())){
				if(useVault){
					if(permission.playerHas(p, this.perm + iid)){
						return true;
					}
					if(Armour.armours.containsKey(iid)){
						return permission.playerHas(p, this.perm + Armour.getArmour(iid).getName());
					} else {
						return false;
					}
				} else {
					if(p.hasPermission(this.perm + iid)){
						return true;
					}
					if(Armour.armours.containsKey(iid)){
						return p.hasPermission(this.perm + Armour.getArmour(iid).getName());
					} else {
						return false;
					}
				}
			} else {
				if(useVault){
					return permission.playerHas(p, this.perm);
				} else {
					return p.hasPermission(this.perm); // If the permission does not need a dv
				}
			}
		}
	}
}
