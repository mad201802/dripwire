package dripwire.commands.cmd;

import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player p) {
            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(Component.text("Du wurdest zum Spawn der Hauptwelt teleportiert.").color(Chat.Color.GREEN));
        }
        return true;
    }
}
