package dripwire.commands.cmd.nick;

import dripwire.commands.cmd.home.HomeManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class NickCmd implements CommandExecutor {

    private final NickManager nickManager = new NickManager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                if(!nickManager.nickExists(args[0])) {
                    nickManager.setNick(p.getUniqueId().toString(), args[0]);
                    NickManager.setGlobalPlayerName(p, args[0]);
                    p.sendMessage(Component.text("Du hast nun den Nickname: " + args[0]));

                    System.out.println(Arrays.toString(nickManager.getNicks().toArray(new String[0])));
                } else {
                    p.sendMessage(Component.text("Dieser Nickname existiert bereits."));
                }
            } else {
                p.sendMessage(Component.text("Du musst einen Nickname angeben."));
            }
        }
        return true;
    }

}
