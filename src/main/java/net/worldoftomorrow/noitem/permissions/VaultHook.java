package net.worldoftomorrow.noitem.permissions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;

public final class VaultHook {
	protected static Permission permission = null;
	protected static boolean loaded;
	
	public VaultHook() {
		loaded = setupPermissions();
	}

	private static boolean setupPermissions() {
		try {
			RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(
							net.milkbowl.vault.permission.Permission.class);
			if (permissionProvider != null) {
				permission = permissionProvider.getProvider();
			}
		} catch (NoClassDefFoundError e) {
			// Do nothing, vault just isn't loaded.
			return false;
		}
		return (permission != null);
	}
}
