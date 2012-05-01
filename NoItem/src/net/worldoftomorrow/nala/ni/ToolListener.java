package net.worldoftomorrow.nala.ni;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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
}
