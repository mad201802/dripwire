package dripwire.commands.cmd.tpa;

import dripwire.Dripwire;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpacancelCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if(TpaCmd.tpas.containsKey(p)) {
                Player target = TpaCmd.tpas.get(p);
                TpaCmd.tpas.remove(p);
                p.sendMessage("Du hast die Tpa Anfrage an " + target.getName() + " abgebrochen");
                target.sendMessage("Die Tpa Anfrage von " + p.getName() + " wurde abgebrochen");
            } else {
                p.sendMessage("Du hast keine Tpa Anfrage");
            }
        }
        return true;
    }
}
