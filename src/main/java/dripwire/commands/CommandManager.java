package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.PingCmd;
import dripwire.commands.cmd.SpawnCmd;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.INSTANCE.getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.INSTANCE.getCommand("ping").setExecutor(new PingCmd());
    }

}
