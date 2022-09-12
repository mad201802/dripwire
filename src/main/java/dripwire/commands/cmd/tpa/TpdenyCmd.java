package dripwire.commands.cmd.tpa;

import dripwire.Dripwire;
import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpdenyCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player requester = p.getServer().getPlayer(args[0]);
                if (requester != null) {
                    if (TpaCmd.tpas.containsKey(requester)) {
                        TpaCmd.tpas.remove(requester);
                        p.sendMessage(Component.text("Du hast die TPA von " + requester.getName() + " abgelehnt").color(Chat.Color.RED));
                        requester.sendMessage(Component.text("Deine TPA an " + p.getName() + " wurde abgelehnt").color(Chat.Color.RED));

                    } else {
                        p.sendMessage(Component.text("Keine TPA von " + requester.getName() + " vorhanden").color(Chat.Color.RED));
                    }
                } else {
                    p.sendMessage(Component.text("Spieler nicht gefunden").color(Chat.Color.RED));
                }
            } else {
                p.sendMessage(Component.text("Usage: /tpdeny <player>").color(Chat.Color.ORANGE));
            }
        }
        return true;
    }
}
