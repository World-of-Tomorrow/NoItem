package net.worldoftomorrow.noitem.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class EntityUtil {
	public static Location getSafeLocation(Block center, int radius) {
		for(int range = 1; range < radius + 1; range++) {
			// Start with the neg. range to encompass a radius, not just half
			// Every X within that range
			for(int x = -(range); x <= range; x++) {
				// Every Y within that X
				for(int y = -(range); y <= range; y++) {
					// Every Z within that Y within that X
					for(int z = -(range); z <= range; z++) {
						Block b = center.getWorld().getBlockAt(new Location(center.getWorld(), center.getX() + x, center.getY() + y, center.getZ() + z));
						// If the bottom block is solid and the block above it is not solid, good enough broseph.
						if(b.getType().isSolid() && !b.getRelative(BlockFace.UP).getType().isSolid()) {
							return new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 0.5D, b.getZ() + 0.5D);
						}
					}
				}
			}
		}
		return null;
	}
}
