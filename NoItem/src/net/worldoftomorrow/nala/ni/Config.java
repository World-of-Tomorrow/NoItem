package net.worldoftomorrow.nala.ni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private static String plugin = "NoItem";
	
	public static enum ConfigFile {
		CONFIG("config.yml");

		private String file;

		private ConfigFile(String file) {
			this.file = file;
		}

		public String getFile() {
			return this.file;
		}
	}

	private static Map<ConfigFile, YamlConfiguration> config = new EnumMap<ConfigFile, YamlConfiguration>(
			ConfigFile.class);
	private static Map<ConfigFile, File> configFile = new EnumMap<ConfigFile, File>(
			ConfigFile.class);
	private static Map<ConfigFile, Boolean> loaded = new EnumMap<ConfigFile, Boolean>(
			ConfigFile.class);

	public static YamlConfiguration getConfig(ConfigFile configfile) {
		if ((loaded.get(configfile) == null)
				|| (!((Boolean) loaded.get(configfile)).booleanValue())) {
			loadConfig(configfile);
		}
		return (YamlConfiguration) config.get(configfile);
	}

	public static File getConfigFile(ConfigFile configfile) {
		return (File) configFile.get(configfile);
	}

	public static boolean getLoaded(ConfigFile configfile) {
		return ((Boolean) loaded.get(configfile)).booleanValue();
	}

	public static void loadConfigs() {
		for (ConfigFile configfile : ConfigFile.values())
			loadConfig(configfile);
	}

	public static void loadConfig(ConfigFile configfile) {
		configFile.put(configfile, new File(Bukkit.getServer()
				.getPluginManager().getPlugin(plugin).getDataFolder(),
				configfile.getFile()));
		if (((File) configFile.get(configfile)).exists()) {
			config.put(configfile, new YamlConfiguration());
			try {
				((YamlConfiguration) config.get(configfile))
						.load((File) configFile.get(configfile));
			} catch (FileNotFoundException ex) {
				loaded.put(configfile, Boolean.valueOf(false));
				return;
			} catch (IOException ex) {
				loaded.put(configfile, Boolean.valueOf(false));
				return;
			} catch (InvalidConfigurationException ex) {
				loaded.put(configfile, Boolean.valueOf(false));
				return;
			}
			loaded.put(configfile, Boolean.valueOf(true));
		} else {
			try {
				Bukkit.getServer().getPluginManager().getPlugin(plugin)
						.getDataFolder().mkdir();
				InputStream jarURL = Config.class.getResourceAsStream("/"
						+ configfile.getFile());
				copyFile(jarURL, (File) configFile.get(configfile));
				config.put(configfile, new YamlConfiguration());
				((YamlConfiguration) config.get(configfile))
						.load((File) configFile.get(configfile));
				loaded.put(configfile, Boolean.valueOf(true));
			} catch (Exception localException) {
			}
		}
	}

	private static void copyFile(InputStream in, File out) throws Exception {
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1)
				fos.write(buf, 0, i);
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null)
				fos.close();
		}
	}
}