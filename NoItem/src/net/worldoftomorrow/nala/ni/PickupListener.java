package net.worldoftomorrow.nala.ni;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener {
	
	private static Log log = new Log();

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {

		Player p = event.getPlayer();
		int iid = event.getItem().getItemStack().getTypeId();
		
		log.debug("PlayerPickupItemEvent fired. ".concat(Integer.toString(iid)));
		if(Perms.NOPICKUP.has(p, event.getItem().getItemStack())){
			event.setCancelled(true);
			event.getItem().setPickupDelay(200);
			if(Configuration.notifyNoPickup()){
				StringHelper.notifyPlayer(p, Configuration.noPickupMessage(), iid);
			}
			StringHelper.notifyAdmin(p, EventTypes.PICKUP, event.getItem().getItemStack());
		} else {
			log.debug("Item can be picked up");
		}
	}
}