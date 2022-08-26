package dripwire.commands.cmd;

import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player p) {
//            p.sendMessage(Component.text("Dein Ping: " + p.getPing() + " ms").color(Chat.Color.GREEN));
            p.sendMessage(Chat.parse("&green;[&red;PING&green;]: &orange;&bold;Dein Ping liegt bei " + p.getPing() + " ms {@run ; /ping ; &orange;&bold;Nochmal?@}", Chat.Color.WHITE));
        }
        return true;
    }
}
