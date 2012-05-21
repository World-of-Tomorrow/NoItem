package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class ArmourListener implements Listener {

	@EventHandler
	public void onArmourEquip(InventoryClickEvent event){
		if(event.getInventory().getType().equals(InventoryType.CRAFTING)){
			
			int iid = event.getCurrentItem().getTypeId();
			Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
			
			if(event.isShiftClick() ){
				if(VaultPerms.Permissions.NOWEAR.has(p, iid)
						&& !p.isOp()
						&& !VaultPerms.Permissions.ALLITEMS.has(p)
						){
					event.setCancelled(true);
				}
				//Shift click checking
			} else {
				if(event.getSlotType().equals(SlotType.ARMOR)){
					if(VaultPerms.Permissions.NOWEAR.has(p, iid)
							&& !p.isOp()
							&& !VaultPerms.Permissions.ALLITEMS.has(p)
							){
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
