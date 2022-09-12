package dripwire.commands.cmd.tpa;

import dripwire.Dripwire;
import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TpaCmd implements CommandExecutor {

    public static HashMap<Player, Player> tpas = new HashMap<>();
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(sender instanceof Player p) {
            if (args.length == 1) {
                Player target = p.getServer().getPlayer(args[0]);
                if (target != null) {
                    if(target.equals(p)) {
                        p.sendMessage(Component.text("Du kannst dich nicht zu dir selbst teleportieren.").color(Chat.Color.RED));
                        return true;
                    }

                    if (!TpaCmd.tpas.containsKey(p)) {
                        TpaCmd.tpas.put(p, target);
                        p.sendMessage(Component.text("TPA an ")
                                .append(Component.text(target.getName()).color(Chat.Color.ORANGE))
                                .append(Component.text(" verschickt.")));

                        TextComponent tpa = Component.text("TPA von ")
                                .append(Component.text(p.getName()).color(Chat.Color.ORANGE))
                                .append(Component.text(": "))
                                .append(Component.text("Annehmen").color(TextColor.color(0x22ff44))
                                        .clickEvent(ClickEvent.runCommand("/tpaccept " + p.getName()))
                                        .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("Click to accept"))))

                                .append(Component.text(" | "))
                                .append(Component.text("Ablehnen").color(Chat.Color.RED)
                                        .clickEvent(ClickEvent.runCommand("/tpdeny " + p.getName()))
                                        .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("Click to deny"))));
                        target.sendMessage(tpa);
                    } else {
                        p.sendMessage(Component.text("Du hast bereits eine TPA an " + target.getName() + " gesendet!").color(Chat.Color.RED));
                    }
                } else {
                    p.sendMessage(Component.text("Spieler nicht gefunden").color(Chat.Color.RED));
                }
            } else {
                p.sendMessage(Component.text("Usage: /tpa <player>").color(Chat.Color.ORANGE));
            }
        }
        return true;
    }
}
