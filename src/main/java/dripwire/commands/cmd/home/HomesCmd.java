package dripwire.commands.cmd.home;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class HomesCmd implements CommandExecutor {

    private final HomeManager homeManager = new HomeManager();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                Set<String> userHomes = homeManager.getHomes(p.getUniqueId().toString());
                if (userHomes.size() == 0) {
                    p.sendMessage(Component.text("Du hast keine Homen."));
                } else {
                    String joinedHomes = String.join(", ", userHomes);
                    p.sendMessage(Component.text("Deine Homes sind: " + joinedHomes));
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
