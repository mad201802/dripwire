package dripwire.commands.cmd.tpa;

import dripwire.Dripwire;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpacceptCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player requester = p.getServer().getPlayer(args[0]);
                if (requester != null) {
                    if (TpaCmd.tpas.containsKey(requester)) {
                        p.sendMessage("Du hast den Tpa von " + requester.getName() + " angenommen");

                        requester.teleport(p);
                        requester.sendMessage("Du wurdest zu " + p.getName() + " teleportiert");
                        TpaCmd.tpas.remove(requester);
                    } else {
                        p.sendMessage("Keine Tpa Anfrage von " + requester.getName() + " vorhanden");
                    }
                } else {
                    p.sendMessage("Spieler nicht gefunden");
                }
            } else {
                p.sendMessage("/tpaccept <player>");
            }
        }
        return true;
    }
}
