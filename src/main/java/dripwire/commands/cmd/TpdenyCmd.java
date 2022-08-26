package dripwire.commands.cmd;

import dripwire.Dripwire;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpdenyCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (Dripwire.INSTANCE.tpas.containsKey(p)) {
                        Dripwire.INSTANCE.tpas.remove(p);
                        p.sendMessage("Du hast den Tpa von " + target.getName() + " abgelehnt");
                        target.sendMessage("Der Tpa zu " + p.getName() + " wurde abgelehnt");
                    } else {
                        p.sendMessage("Keine Tpa Anfrage von " + target.getName() + " vorhanden");
                    }
                } else {
                    p.sendMessage("Spieler nicht gefunden");
                }
            } else {
                p.sendMessage("/tpdeny <player>");
            }
        }
        return true;
    }
}
