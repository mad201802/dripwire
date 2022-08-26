package dripwire.util;

import com.google.common.base.Charsets;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class on top of {@link YamlConfiguration} to better manage YAML files.
 * @author avaze
 * @version 1.0
 */
public class ConfigFile extends YamlConfiguration {
    public enum Type {
        /**
         * The config is only loading defaults from the resource folder, and not saved to the data folder.
         */
        LOAD_ONLY,
        /**
         * The config is only being saved to the data folder, and no defaults are loaded.
         */
        SAVE_ONLY,
        /**
         * The config is loading defaults from the resource folder, and saving to the data folder.
         */
        LOAD_AND_SAVE
    }


    private static Plugin plugin;

    /**
     * Sets the {@link Plugin} that config files belong to.
     * @param plugin The plugin
     */
    public static void setPlugin(Plugin plugin) {
        ConfigFile.plugin = plugin;
    }

    private final File configFile; // File object for the saved file in the data fodler
    private final String name; // File name for the saved file
    private final Type type;

    /**
     * Creates a new {@link ConfigFile}.
     * @param name The name of the config file (only .yml files, extension can be omitted).
     * @param type The type of the config file.
     */
    public ConfigFile(String name, Type type) {
        if (plugin == null) throw new RuntimeException("ConfigFile plugin not set, use ConfigFile.setPlugin() in your onEnable method");
        if (name == null) throw new IllegalArgumentException("Filename cannot be null");

        if (!name.toLowerCase().endsWith(".yml")) {
            name += ".yml";
        }

        this.name = name;
        this.type = type;
        this.configFile = new File(plugin.getDataFolder(), name);

        try {
            if (type != Type.SAVE_ONLY) {
                // Get default config file from plugin resource folder
                final InputStream defConfigStream = plugin.getResource(name);
                if (defConfigStream == null) {
                    System.err.println("ERROR: Plugin resource '" + name + "' not found.");
                    return;
                }
                setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
            }
            if (type != Type.LOAD_ONLY) {
                // Create file if it doesn't exist yet
                if (!configFile.exists()) configFile.createNewFile();
                // Load the existing file
                load(configFile);
                save();
            }

        } catch (IOException e) {
            System.err.println("ERROR: Could not load config '" + name + "'");
        } catch (InvalidConfigurationException e) {
            System.err.println("ERROR: Invalid config in file '" + name + "'");
        }
    }

    /**
     * Create a new {@link ConfigFile} with defaults from the resource folder & save it to the plugin data folder.
     * @param name The name of the config file (only .yml files, extension can be omitted).
     */
    public ConfigFile(String name) {
        this(name, Type.LOAD_AND_SAVE);
    }

    /**
     * Save the {@link ConfigFile} to the plugin data folder.
     */
    public void save() {
        if (type == Type.LOAD_ONLY) return;
        try {
            this.save(configFile);
        } catch (IOException e) {
            System.err.println("ERROR: Could not save config '" + name + "'");
        }
    }

    /**
     * Reload the config file from the plugin data folder.
     * Call this when you suspect another {@link ConfigFile} instance has changed the config file.
     */
    public void reload() {
        if (type == Type.LOAD_ONLY) {
            System.err.println("WARNING: Tried to reload config '" + name + "' with LOAD_ONLY config.");
            return;
        }
        try {
            this.load(configFile);
        } catch (IOException e) {
            System.err.println("ERROR: Could not load config '" + name + "'");
        } catch (InvalidConfigurationException e) {
            System.err.println("ERROR: Invalid config in file '" + name + "'");
        }
    }

    /**
     * Gets the default config file from the resource folder and saves it to the data folder.
     * Note that this won't replace the file if it already exists,
     * use {@link ConfigFile#delete} to delete the file first if you want to replace it.
     */
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            try {
                plugin.saveResource(name, false);
            } catch (Exception e) {
                System.err.println("ERROR: Could not find resource '" + name + "'");
            }
        }
    }

    /**
     * Delete the config file from the plugin data folder.
     */
    public void delete() {
        if (configFile.exists()) {
            boolean failed = !configFile.delete();
            if (failed) System.err.println("ERROR: Could not delete config '" + name + "'");
        }
    }
}
