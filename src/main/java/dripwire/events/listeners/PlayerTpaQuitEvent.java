package dripwire.events.listeners;

import dripwire.Dripwire;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerTpaQuitEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(Dripwire.get().tpas.containsKey(event.getPlayer())) {
            Player player = event.getPlayer();
            Player target = Dripwire.get().tpas.get(player);

            Dripwire.get().tpas.remove(player);
            target.sendMessage("Die Tpa Anfrage von " + player.getName() + " wurde abgebrochen");
        }

        if(Dripwire.get().tpas.containsValue(event.getPlayer())) {
            Player player = event.getPlayer();
            Player target = Dripwire.get().tpas.get(player);

            Dripwire.get().tpas.remove(target);
            player.sendMessage("Die Tpa Anfrage an " + target.getName() + " wurde abgebrochen");
        }
    }
}
