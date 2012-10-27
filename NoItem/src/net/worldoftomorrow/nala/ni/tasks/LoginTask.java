package net.worldoftomorrow.nala.ni.tasks;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LoginTask implements Runnable {

	final Player p;

	public LoginTask(Player p) {
		this.p = p;
	}

	public void run() {
		World w = p.getWorld();
		ItemStack stack = new ItemStack(p.getItemInHand());
		int heldSlot = p.getInventory().getHeldItemSlot();
		p.getInventory().setItem(heldSlot, null);
		w.dropItemNaturally(p.getLocation(), stack);
	}

}
