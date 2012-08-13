package net.worldoftomorrow.nala.ni.tasks;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LoginTask implements Runnable {

    Player p;

    public LoginTask(Player p) {
        this.p = p;
    }
    
    //@Override
    public void run() {
        World w = p.getWorld();
        ItemStack stack = p.getItemInHand();
        ItemStack drop = new ItemStack(stack);
        p.getInventory().setItem(0, null); // Don't hardcode this after 1.3
        w.dropItemNaturally(p.getLocation(), drop);
    }

}
