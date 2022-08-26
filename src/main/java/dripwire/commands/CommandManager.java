package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.*;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.get().getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.get().getCommand("ping").setExecutor(new PingCmd());
        Dripwire.get().getCommand("tpa").setExecutor(new TpaCmd());
        Dripwire.get().getCommand("tpaccept").setExecutor(new TpacceptCmd());
        Dripwire.get().getCommand("tpdeny").setExecutor(new TpdenyCmd());
        Dripwire.get().getCommand("tpcancel").setExecutor(new TpacancelCmd());
    }

}
