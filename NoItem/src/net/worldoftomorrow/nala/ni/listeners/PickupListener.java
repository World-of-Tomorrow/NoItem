package net.worldoftomorrow.nala.ni.listeners;

import net.worldoftomorrow.nala.ni.EventTypes;
import net.worldoftomorrow.nala.ni.Perms;
import net.worldoftomorrow.nala.ni.StringHelper;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PickupListener implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {

        Player p = event.getPlayer();
        ItemStack stack = event.getItem().getItemStack();
        if (Perms.NOPICKUP.has(p, stack)) {
            event.setCancelled(true);
            event.getItem().setPickupDelay(100);
            StringHelper.notifyPlayer(p, EventTypes.PICKUP, stack);
            StringHelper.notifyAdmin(p, EventTypes.PICKUP, stack);
        }
    }
}