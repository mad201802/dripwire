package dripwire.commands.cmd;

import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
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
            p.sendMessage(Chat.parse("&green;[&red;PING&green;]: &orange;&bold;Dein Ping liegt bei " + p.getPing() + " ms. ")
                    .append(Chat.interactive("Nochmal?", ClickEvent.runCommand("/ping"), HoverEvent.showText(Component.text("Klicke hier um deinen Ping erneut zu prüfen.")))));
        }
        return true;
    }
}
