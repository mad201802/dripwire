package dripwire.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to the server!");
    }

}
