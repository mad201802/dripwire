package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.SpawnCmd;
import dripwire.commands.cmd.home.SethomeCmd;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.get().getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.get().getCommand("sethome").setExecutor(new SethomeCmd());
    }

}
