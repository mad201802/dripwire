package dripwire.events.listeners;

import dripwire.Dripwire;
import dripwire.commands.cmd.tpa.TpaCmd;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerTpaQuitEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(TpaCmd.tpas.containsKey(event.getPlayer())) {
            Player player = event.getPlayer();
            Player target = TpaCmd.tpas.get(player);

            TpaCmd.tpas.remove(player);
            target.sendMessage("Die Tpa Anfrage von " + player.getName() + " wurde abgebrochen");
        }

        if(TpaCmd.tpas.containsValue(event.getPlayer())) {
            Player player = event.getPlayer();
            Player target = TpaCmd.tpas.get(player);

            TpaCmd.tpas.remove(target);
            player.sendMessage("Die Tpa Anfrage an " + target.getName() + " wurde abgebrochen");
        }
    }
}
