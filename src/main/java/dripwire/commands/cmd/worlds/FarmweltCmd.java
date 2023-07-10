package dripwire.commands.cmd.worlds;

import dripwire.util.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class FarmweltCmd implements CommandExecutor {

    public static String FARMWELT_NAME = "farmwelt";

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if(args.length == 0) {
                if(Bukkit.getWorld(FARMWELT_NAME) == null) {
                    p.sendMessage(Component.text("Bitte warte bis die Farmwelt erstellt wurde ...").color(Chat.Color.ORANGE));

                    createFarmwelt();

                    p.sendMessage(Component.text("Die Farmwelt wurde erstellt!").color(Chat.Color.GREEN));
                }

                p.teleport(Bukkit.getWorld(FARMWELT_NAME).getSpawnLocation());
                p.sendMessage(Component.text("!!! Bitte beachte !!!").color(Chat.Color.RED));
                p.sendMessage(Component.text("Aufgrund eines Fehlers werden in der Hautwelt werden keine Minecraft Dungeons generiert.").color(Chat.Color.ORANGE));
                p.sendMessage(Component.text("Um gewisse Items trotzdem bekommen zu können, existiert diese Welt.").color(Chat.Color.ORANGE));
                p.sendMessage(Component.text("Die Farmwelt ist kein Platz zum bauen! Sie kann jederzeit gelöscht werden.").color(Chat.Color.ORANGE));
                p.sendMessage(Component.text("Mit /spawn kommst du wieder in die Hauptwelt").color(Chat.Color.ORANGE));

            } else if(args.length == 1 && p.isOp()) {
                if(args[0].equalsIgnoreCase("create")) {
                    if(Bukkit.getWorld(FARMWELT_NAME) == null) {
                        p.sendMessage(Component.text("Bitte warte bis die Farmwelt erstellt wurde ...").color(Chat.Color.ORANGE));

                        createFarmwelt();

                        p.sendMessage(Component.text("Die Farmwelt wurde erstellt!").color(Chat.Color.GREEN));
                    } else {
                        p.sendMessage(Component.text("Die Farmwelt existiert bereits!").color(Chat.Color.RED));
                    }
                } else if(args[0].equalsIgnoreCase("delete")) {
                    if(Bukkit.getWorld(FARMWELT_NAME) != null) {
                        p.sendMessage(Component.text("Um die Welt zu löschen muss der Server gestoppt werden. Lösche anschließend den Ordner mit dem Name '" + FARMWELT_NAME + "' per FTP.").color(Chat.Color.ORANGE));
                    } else {
                        p.sendMessage(Component.text("Die Farmwelt existiert nicht!").color(Chat.Color.RED));
                    }
                }
            }
        }
        return true;
    }

    private void createFarmwelt() {
        WorldCreator wc = new WorldCreator(FARMWELT_NAME);
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.NORMAL);
        wc.generateStructures(true);
        wc.createWorld();
    }

}
