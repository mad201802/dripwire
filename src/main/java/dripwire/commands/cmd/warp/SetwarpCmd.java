package dripwire.commands.cmd.warp;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetwarpCmd implements CommandExecutor {

    private final WarpManager warpManager = new WarpManager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p && p.isOp()) {
            if (args.length == 1) {
                if(!warpManager.warpExists(args[0])) {
                    warpManager.setWarp(args[0], p.getLocation());
                    p.sendMessage(Component.text("Du hast den Warp " + args[0] + " gesetzt."));
                } else {
                    p.sendMessage(Component.text("Es existiert bereits ein Warp mit diesem Name."));
                }
            } else {
                p.sendMessage(Component.text("Du musst einen Namen angeben."));
            }
        }
        return true;
    }

}
