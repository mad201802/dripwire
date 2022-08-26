package dripwire.commands.cmd;

import dripwire.Dripwire;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpaCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target != null) {
                    if(target.equals(p)) {
                        p.sendMessage("Du kannst dich nicht zu dir selber teleportieren!");
                        return true;
                    }

                    if (!Dripwire.get().tpas.containsKey(p)) {
                        Dripwire.get().tpas.put(p, target);
                        p.sendMessage("Du hast eine Tpa Anfrage zu " + target.getName() + " gesendet");

                        target.sendMessage("Du hast einen Tpa von " + p.getName() + " erhalten.");
                        target.sendMessage("Benutze /tpaccept " + p.getName() + " um den Tpa anzunehmen");
                    } else {
                        p.sendMessage("Du hast bereits einen Tpa an " + target.getName() + " gesendet");
                    }
                } else {
                    p.sendMessage("Spieler nicht gefunden");
                }
            } else {
                p.sendMessage("/tpa <player>");
            }
        }
        return true;
    }
}
