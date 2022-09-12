package dripwire.commands.cmd.nick;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class UnnickCmd implements CommandExecutor {
    private final NickManager nickManager = new NickManager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                nickManager.deleteNick(p.getUniqueId().toString());
                NickManager.resetPlayerName(p);
                p.sendMessage(Component.text("Du hast nun keinen Nickname mehr"));
            } else {
                p.sendMessage(Component.text("Bitte benutze nur /unnick"));
            }
        }
        return true;
    }
}
