package dripwire.commands.cmd.home;

import dripwire.Dripwire;
import dripwire.util.ConfigFile;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class HomeManager {

    private final ConfigFile homeConfig;

    private static HomeManager instance;
    public static HomeManager get() {
        return instance;
    }

    public HomeManager() {
        if(instance == null) instance = this;
        homeConfig = new ConfigFile("homes.yml", ConfigFile.Type.LOAD_AND_SAVE);
        homeConfig.saveDefaultConfig();
    }

    public boolean setHome(String uuid, String homeName, Location location) {
        homeConfig.set(uuid + "." + homeName, location.serialize());

        try {
            homeConfig.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Location getHome(String uuid, String homeName) {
        if(homeExists(uuid, homeName)) {
            ConfigurationSection section = homeConfig.getConfigurationSection(uuid + "." + homeName);
            return Location.deserialize(section.getValues(false));
        }
        return null;
    }

    public boolean deleteHome(String uuid, String homeName) {
        if(homeExists(uuid, homeName)) {
            homeConfig.set(uuid + "." + homeName, null);
            try {
                homeConfig.save();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Set<String> getHomes(String uuid) {
        homeConfig.reload();

        ConfigurationSection userSection = homeConfig.getConfigurationSection(uuid);
        if(userSection == null) return Collections.emptySet();

        return userSection.getKeys(false);
    }

    public boolean homeExists(String uuid, String homeName) {
        Set<String> userHomes = getHomes(uuid);
        return userHomes.contains(homeName);
    }
}
