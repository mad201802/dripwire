package dripwire;

import dripwire.commands.CommandManager;
import dripwire.events.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Dripwire extends JavaPlugin {

    public HashMap<Player, Player> tpas = new HashMap<>();

    private static Dripwire INSTANCE;
    public static Dripwire get() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {

        // setup for ConfigFile Helper
        ConfigFile.setPlugin(this);

        letTheWholeWorldKnowDripwireIsHere();

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


    /**
     * <h1>Let 'em know</h1>
     */
    private void letTheWholeWorldKnowDripwireIsHere() {

        ConfigFile messages = new ConfigFile("messages.yml", ConfigFile.Type.LOAD_AND_SAVE);
        messages.saveDefaultConfig();

        List<?> msgs = messages.getList("messages");
        if (msgs == null) {
            getLogger().severe("Could not load messages");
            return;
        }

        // Get random message
        String msg = (String) msgs.get((int) (Math.random() * msgs.size()));
        String divider = "-----------------------------------------------------";

        Bukkit.getScheduler().runTaskLater(this, () -> Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage("");
            player.sendMessage("§7---------------------- §f§lDripwire§7 ----------------------");
            player.sendMessage("");
            player.sendMessage(StringUtils.rainbow(msg, 2));
            player.sendMessage("");
            player.sendMessage("§7" + divider);
            logInfo(divider+divider);
            logInfo(msg);
            logInfo(divider+divider);
        }), 20L);
    }

    public void logInfo(String msg) {
        getLogger().info(msg);
    }

    public void logWarning(String msg) {
        getLogger().warning(msg);
    }

    public void logError(String msg) {
        getLogger().severe(msg);
    }
}
