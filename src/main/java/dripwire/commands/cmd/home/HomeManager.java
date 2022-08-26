package dripwire.commands.cmd.home;

import dripwire.util.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class HomeManager {

    private ConfigFile homeConfig = new ConfigFile("homes.yml", ConfigFile.Type.LOAD_AND_SAVE);

    private static HomeManager instance;
    public static HomeManager get() {
        return instance;
    }

    public HomeManager() {
        if(instance == null) instance = this;
    }

    public boolean setHome(String uuid, String homeName, String world, double x, double y, double z, float yaw, float pitch) {
        if(homeConfig.getConfigurationSection(uuid) == null) {
            homeConfig.createSection(uuid);
        }
        homeConfig.saveDefaultConfig();
        return true;
    }

    public boolean deleteHome(String uuid, String homeName) {
        return false;
    }

    public ArrayList<String> getHomes(String uuid) {
        return null;
    }

    private int getUserHomes(String uuid) {
        return homeConfig.getList("homes." + uuid).size();
    }

}
