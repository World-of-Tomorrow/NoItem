package net.worldoftomorrow.nala.ni;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolListener implements Listener{
	
	private Log log = new Log();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		
		Player p = event.getPlayer();
		int id = p.getItemInHand().getTypeId();
		
		if(Configuration.debugging()){
			log.log("Block Break event fired. \nUsed: " + id);
		}
		if(Permissions.NOUSE.has(p, id) && !p.isOp() && !Permissions.ALLITEMS.has(p)){
			event.setCancelled(true);
			if(Configuration.notifyNoUse()){
				p.sendMessage(ChatColor.BLUE + Configuration.noUseMessage());
			}
		}
	}
	
	@EventHandler
	public void onLandFarm(PlayerInteractEvent event){
		Block block = event.getClickedBlock();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(block.getType() == Material.DIRT || block.getType() == Material.GRASS){
				int inHand = event.getPlayer().getItemInHand().getTypeId();
				Player p = event.getPlayer();
				if(inHand >= 290 && inHand <= 294){
					if(Permissions.NOUSE.has(p, inHand) && !p.isOp() && !Permissions.ALLITEMS.has(p)){
						event.setCancelled(true);
						if(Configuration.notifyNoUse()){
							p.sendMessage(ChatColor.BLUE + Configuration.noUseMessage());
						}
					}
				}
			}
		}
	}
}
