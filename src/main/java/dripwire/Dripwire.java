package dripwire;

import dripwire.commands.CommandManager;
import dripwire.events.EventManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Dripwire extends JavaPlugin {

    public HashMap<Player, Player> tpas = new HashMap<>();

    public static Dripwire INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Dripwire enabled");

        INSTANCE = this;

        CommandManager.registerCommands();
        EventManager eventManager = new EventManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
