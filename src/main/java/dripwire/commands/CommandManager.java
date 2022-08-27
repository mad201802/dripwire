package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.SpawnCmd;
import dripwire.commands.cmd.home.DelhomeCmd;
import dripwire.commands.cmd.home.HomeCmd;
import dripwire.commands.cmd.home.HomesCmd;
import dripwire.commands.cmd.home.SethomeCmd;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.get().getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.get().getCommand("sethome").setExecutor(new SethomeCmd());
        Dripwire.get().getCommand("delhome").setExecutor(new DelhomeCmd());
        Dripwire.get().getCommand("home").setExecutor(new HomeCmd());
        Dripwire.get().getCommand("homes").setExecutor(new HomesCmd());
    }

}
