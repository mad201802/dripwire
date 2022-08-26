package dripwire.commands.cmd;

import dripwire.Dripwire;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpacceptCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (Dripwire.INSTANCE.tpas.containsKey(p)) {
                        p.sendMessage("Du hast den Tpa von " + target.getName() + " angenommen");

                        target.teleport(p);
                        target.sendMessage("Du wurdest zu " + p.getName() + " teleportiert");
                        Dripwire.INSTANCE.tpas.remove(target);
                    } else {
                        p.sendMessage("Keine Tpa Anfrage von " + target.getName() + " vorhanden");
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
