package net.worldoftomorrow.nala.ni;

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
	NOCOOK("noitem.nocook.");

	private String perm;

	private Perms(String perm) {
		this.perm = perm;
	}
	
	public String getPerm(){
		return this.perm;
	}
	
	public boolean has(Player p){
		if(perm.equalsIgnoreCase(Perms.ALLITEMS.perm) || perm.equalsIgnoreCase(Perms.ADMIN.perm)){
			//Do the Op check here to avoid needing to run it elsewhere.
			if(p.isOp()){ return true; }
		}
		if(Vault.vaultPerms){
			return Vault.has(p, this.perm);
		} else {
			return p.hasPermission(this.perm);
		}
	}
	
	public boolean has(Player p, ItemStack stack){
		//Check if the have the ALLITEMS permission first to prevent unneeded code execution.
		int itemId = 0;
		int data = 0;
		if(stack != null){
			itemId = stack.getTypeId();
			data = stack.getDurability();
		}
		if(Perms.ALLITEMS.has(p)){
			return false;
		}
		String permission = "";
		if(perm.equalsIgnoreCase(Perms.NOCRAFT.perm)
				|| perm.equalsIgnoreCase(Perms.NOPICKUP.perm)
				|| perm.equalsIgnoreCase(Perms.NODROP.perm)
				|| perm.equalsIgnoreCase(Perms.NOHOLD.perm)
				|| perm.equalsIgnoreCase(Perms.NOUSE.perm)
				|| perm.equalsIgnoreCase(Perms.NOCOOK.perm)){
			if(data > 0){
				permission = this.perm.concat(Integer.toString(itemId)).concat(".").concat(Integer.toString(data));
			} else {
				permission = this.perm.concat(Integer.toString(itemId));
			}
			//Use separate ELSE IF for NOBREW, because you want to check for 0 as a data value.
		} else if(perm.equalsIgnoreCase(Perms.NOBREW.perm)) {
			permission = this.perm.concat(Integer.toString(data)).concat(".").concat(Integer.toString(itemId));
		} else {
			permission = this.perm.concat(Integer.toString(itemId)).concat(".").concat(Integer.toString(data));
		}
		
		if(Vault.vaultPerms){
			if(Vault.has(p, permission)){
				return true;
				//If it is a permission that supports item names
			} else if (perm.equalsIgnoreCase(Perms.NOCRAFT.perm)
					|| perm.equalsIgnoreCase(Perms.NOPICKUP.perm)
					|| perm.equalsIgnoreCase(Perms.NODROP.perm)
					|| perm.equalsIgnoreCase(Perms.NOHOLD.perm)
					|| perm.equalsIgnoreCase(Perms.NOUSE.perm)
					|| perm.equalsIgnoreCase(Perms.NOCOOK.perm)){
				if(Tools.isTool(itemId)){
					return Vault.has(p, this.perm.concat(Tools.getTool(itemId).getName()));
				} else if (Armor.isArmor(itemId)){
					return Vault.has(p, this.perm.concat(Armor.getArmour(itemId).getName()));
				} else if (Cookable.items.containsKey(itemId)) {
					return Vault.has(p, this.perm.concat(Cookable.items.get(itemId).getName()));
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			if (p.hasPermission(permission)) {
				return true;
			} else if (perm.equalsIgnoreCase(Perms.NOCRAFT.perm)
					|| perm.equalsIgnoreCase(Perms.NOPICKUP.perm)
					|| perm.equalsIgnoreCase(Perms.NOHOLD.perm)
					|| perm.equalsIgnoreCase(Perms.NOUSE.perm)
					|| perm.equalsIgnoreCase(Perms.NOCOOK.perm)){
				if (Tools.isTool(itemId)) {
					return p.hasPermission(this.perm.concat(Tools.getTool(
							itemId).getName()));
				} else if (Armor.isArmor(itemId)) {
					return p.hasPermission(this.perm.concat(Armor.getArmour(itemId).getName()));
				} else if (Cookable.items.containsKey(itemId)) {
					return p.hasPermission(this.perm.concat(Cookable.items.get(itemId).getName()));
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	public boolean has(Player p, int data, int ingredient){
		if(Perms.ALLITEMS.has(p)){
			return false;
		}
		String permission;
		if(perm.equalsIgnoreCase(Perms.NOBREW.perm)) {
			permission = this.perm.concat(Integer.toString(data)).concat(".").concat(Integer.toString(ingredient));
		} else {
			permission = this.perm.concat(Integer.toString(data)).concat(".").concat(Integer.toString(ingredient));
		}
		if(Vault.vaultPerms){
			return Vault.has(p, permission);
		} else {
			return p.hasPermission(permission);
		}
	}
	
	public boolean has(Player p, int itemId){
		if(Perms.ALLITEMS.has(p)){
			return false;
		}
		if(perm.equalsIgnoreCase(Perms.NOWEAR.perm)){
			if(Vault.vaultPerms){
				if(Vault.has(p, perm.concat(Integer.toString(itemId)))){
					return true;
				} else {
					if(Armor.isArmor(itemId)){
						return Vault.has(p, perm.concat(Armor.getArmour(itemId).getName()));
					} else {
						return false;
					}
				}
			} else {
				if(p.hasPermission(perm.concat(Integer.toString(itemId)))){
					return true;
				} else {
					if(Armor.isArmor(itemId)){
						return p.hasPermission(perm.concat(Armor.getArmour(itemId).getName()));
					} else {
						return false;
					}
				}
			}
		} else {
			if(Vault.vaultPerms){
				return Vault.has(p, perm.concat(Integer.toString(itemId)));
			} else {
				return p.hasPermission(perm.concat(Integer.toString(itemId)));
			}
		}
	}
}
