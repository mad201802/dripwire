package dripwire.commands.cmd.home;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SethomeCmd implements CommandExecutor {
    private final HomeManager homeManager = new HomeManager();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                if(!homeManager.homeExists(p.getUniqueId().toString(), args[0])) {
                    homeManager.setHome(p.getUniqueId().toString(), args[0], p.getLocation());
                    p.sendMessage(Component.text("Du hast dein Home " + args[0] + " gesetzt."));
                } else {
                    p.sendMessage(Component.text("Du hast bereits ein Home mit diesem Namen."));
                }
            } else {
                p.sendMessage(Component.text("Du musst einen Namen angeben."));
            }
        }
        return true;
    }
}
