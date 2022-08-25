package dripwire;

import dripwire.commands.CommandManager;
import dripwire.events.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dripwire extends JavaPlugin {

    public static Dripwire INSTANCE;

    private EventManager eventManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Dripwire enabled");

        INSTANCE = this;

        CommandManager.registerCommands();
        eventManager = new EventManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
