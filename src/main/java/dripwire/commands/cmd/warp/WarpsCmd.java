package dripwire.commands.cmd.warp;

import dripwire.commands.cmd.home.HomeManager;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class WarpsCmd implements CommandExecutor {
    private final WarpManager warpManager = new WarpManager();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                Set<String> warps = warpManager.getWarps();
                if (warps.size() == 0) {
                    p.sendMessage(Component.text("Es existieren keine Warps."));
                } else {
                    String joinedWarps = String.join(", ", warps);
                    p.sendMessage(Component.text("Alle Warps: " + joinedWarps));
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
