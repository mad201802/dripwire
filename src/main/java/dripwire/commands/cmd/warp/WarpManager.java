package dripwire.commands.cmd.warp;

import dripwire.util.ConfigFile;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.Set;

public class WarpManager {

    private final ConfigFile warpManager;

    private static WarpManager instance;
    public static WarpManager get() {
        return instance;
    }

    public WarpManager() {
        if(instance == null) instance = this;
        warpManager = new ConfigFile("warps.yml", ConfigFile.Type.LOAD_AND_SAVE);
        warpManager.saveDefaultConfig();
    }

    public boolean setWarp(String warpName, Location location) {
        warpManager.set(warpName, location.serialize());

        try {
            warpManager.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Location getWarp(String warpName) {
        if(warpExists(warpName)) {
            ConfigurationSection section = warpManager.getConfigurationSection(warpName);
            return Location.deserialize(section.getValues(false));
        }
        return null;
    }

    public boolean deleteWarp(String warpName) {
        if(warpExists(warpName)) {
            warpManager.set(warpName, null);
            try {
                warpManager.save();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Set<String> getWarps() {
        warpManager.reload();
        return warpManager.getKeys(false);
    }

    public boolean warpExists(String warpName) {
        Set<String> userHomes = getWarps();
        return userHomes.contains(warpName);
    }

}
