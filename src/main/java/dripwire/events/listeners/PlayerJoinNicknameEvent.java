package dripwire.events.listeners;

import dripwire.commands.cmd.nick.NickManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinNicknameEvent implements Listener {

    private NickManager nickManager = new NickManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        boolean hasNick = nickManager.hasNick(p.getUniqueId().toString());
        if(hasNick) {
            NickManager.setGlobalPlayerName(p, nickManager.getNick(p.getUniqueId().toString()));
        } else {
            p.sendMessage(Component.text("Du hast noch keinen Nickname gesetzt! Benutze bitte /nick <name>"));
        }
    }

}
