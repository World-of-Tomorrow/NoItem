package net.worldoftomorrow.nala.ni;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class InHandListener implements Listener{
	
	Log log = new Log();
	
	private boolean debug = Configuration.debugging();
	
	@EventHandler
	public void onItemSwitch(PlayerItemHeldEvent event){
		if(debug){
			log.log("PlayerItemHeldEvent fired. old: " + event.getPreviousSlot() + " new: " + event.getNewSlot());
		}
		
		Player p = event.getPlayer();
		int ns = event.getNewSlot();
		int ps = event.getPreviousSlot();
		int iid = 0;
		ItemStack notAllowed = null;
		ItemStack allowed = null;
		if(p.getInventory().getItem(ns) != null){
			iid = p.getInventory().getItem(ns).getTypeId();
			notAllowed = p.getInventory().getItem(ns);
		}
		if(p.getInventory().getItem(ps) != null){
			allowed = p.getInventory().getItem(ps);
		}
		//Switch the items.
		if(VaultPerms.Permissions.NOHOLD.has(p, iid) && !p.isOp() && !VaultPerms.Permissions.ALLITEMS.has(p)){
			p.getInventory().setItem(ns, allowed);
			p.getInventory().setItem(ps, notAllowed);
			if(Configuration.notifyNoHold()){
				notifyPlayer(p, iid);
			}
			if(Configuration.notifyAdmins()){
				notifyAdmin(p, iid);
			}
		}
	}
	
	@EventHandler
	public void onItemInvPlace(InventoryClickEvent event){
		if(event.getSlotType().equals(SlotType.QUICKBAR)){
			Player p = Bukkit.getPlayer(event.getWhoClicked().getName());
			int iid = 0;
			if(event.getCursor() != null){
				iid = event.getCursor().getTypeId();
			}
			if(VaultPerms.Permissions.NOHOLD.has(p, iid) && !p.isOp() && !VaultPerms.Permissions.ALLITEMS.has(p)){
				event.setCancelled(true);
				if(Configuration.notifyNoHold()){
					notifyPlayer(p, iid);
				}
				if(Configuration.notifyAdmins()){
					notifyAdmin(p, iid);
				}
			}
		}
	}
	
	private void notifyPlayer(Player p, int iid) {
		String dn = p.getDisplayName();
		String w = p.getWorld().getName();

		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();

		p.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
				+ StringHelper.replaceVars(Configuration.noHoldMessage(), dn, w, x, y, z, iid));
	}

	private void notifyAdmin(Player p, int iid) {
		String dn = p.getDisplayName();
		String w = p.getWorld().getName();

		int x = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int z = p.getLocation().getBlockZ();
		String formatedMessage = StringHelper.replaceVars(Configuration.adminMessage(), dn, w, x, y,
				z, iid);

		log.log(formatedMessage);
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players)
			if ((player.isOp()) || (VaultPerms.Permissions.ADMIN.has(player)))
				player.sendMessage(ChatColor.RED + "[NI] " + ChatColor.BLUE
						+ formatedMessage);
	}
}
