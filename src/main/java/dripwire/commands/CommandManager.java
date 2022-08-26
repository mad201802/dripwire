package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.*;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.INSTANCE.getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.INSTANCE.getCommand("ping").setExecutor(new PingCmd());
        Dripwire.INSTANCE.getCommand("tpa").setExecutor(new TpaCmd());
        Dripwire.INSTANCE.getCommand("tpaccept").setExecutor(new TpacceptCmd());
        Dripwire.INSTANCE.getCommand("tpdeny").setExecutor(new TpdenyCmd());
        Dripwire.INSTANCE.getCommand("tpcancel").setExecutor(new TpacancelCmd());
    }

}
