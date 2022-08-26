package dripwire.commands.cmd;

import dripwire.Dripwire;
import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class TpacancelCmd implements CommandExecutor {
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if(Dripwire.INSTANCE.tpas.containsKey(p)) {
                Player target = Dripwire.INSTANCE.tpas.get(p);
                Dripwire.INSTANCE.tpas.remove(p);
                p.sendMessage(Component.text("Du hast deine TPA an " + target.getName() + " abgebrochen.").color(Chat.Color.GREEN));
                target.sendMessage(Component.text(p.getName() + " hat die TPA abgebrochen.").color(Chat.Color.GREEN));
            } else {
                p.sendMessage(Component.text("Du hast keine TPA gesendet.").color(Chat.Color.RED));
            }
        }
        return true;
    }
}
