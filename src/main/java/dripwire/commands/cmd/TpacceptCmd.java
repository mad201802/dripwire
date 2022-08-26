package dripwire.commands.cmd;

import dripwire.Dripwire;
import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpacceptCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player requester = p.getServer().getPlayer(args[0]);
                if (requester != null) {
                    if (Dripwire.INSTANCE.tpas.containsKey(requester)) {
                        p.sendMessage(Component.text("Du hast den Tpa von " + requester.getName() + " angenommen").color(Chat.Color.GREEN));

                        requester.teleport(p);
                        requester.sendMessage(Component.text("Du wurdest zu " + p.getName() + " teleportiert").color(Chat.Color.GREEN));
                        Dripwire.INSTANCE.tpas.remove(requester);
                    } else {
                        p.sendMessage(Component.text("Keine TPA von " + requester.getName() + " vorhanden").color(Chat.Color.RED));
                    }
                } else {
                    p.sendMessage(Component.text("Spieler nicht gefunden").color(Chat.Color.RED));
                }
            } else {
                p.sendMessage(Component.text("Usage: /tpaccept <player>").color(Chat.Color.ORANGE));
            }
        }
        return true;
    }
}
