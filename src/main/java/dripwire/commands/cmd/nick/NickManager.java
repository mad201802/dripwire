package dripwire.commands.cmd.nick;

import dripwire.util.ConfigFile;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class NickManager {

    private final ConfigFile nickConfig;

    private static NickManager instance;
    public static NickManager get() {
        return instance;
    }

    public NickManager() {
        if(instance == null) instance = this;
        nickConfig = new ConfigFile("nicks.yml", ConfigFile.Type.LOAD_AND_SAVE);
        nickConfig.saveDefaultConfig();
    }

    public boolean setNick(String uuid, String nickname) {
        nickConfig.set(uuid, nickname);

        try {
            nickConfig.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getNick(String uuid) {
        nickConfig.reload();

        return (String) nickConfig.get(uuid);
    }

    public boolean deleteNick(String uuid) {
        nickConfig.set(uuid, null);
        try {
            nickConfig.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getNicks() {
        nickConfig.reload();

        ArrayList<String> nicks = new ArrayList<>();
        nickConfig.getValues(false).values().forEach(nick -> {
            nicks.add(String.valueOf(nick));
        });

        return nicks;
    }

    public boolean nickExists(String nickname) {
        ArrayList<String> nicks = getNicks();
        return nicks.contains(nickname);
    }

    public boolean hasNick(String uuid) {
        nickConfig.reload();
        return nickConfig.get(uuid) != null;
    }

    public static void setGlobalPlayerName(Player p, String name) {
        String newName = "[" + name + "] " + p.getName();
        p.displayName(Component.text(name));
        p.playerListName(Component.text(newName));
        p.customName(Component.text(name));
    }

    public static void resetPlayerName(Player p) {
        String name = p.getPlayerProfile().getName();
        p.displayName(Component.text(name));
        p.playerListName(Component.text(name));
        p.customName(Component.text(name));
    }

}
