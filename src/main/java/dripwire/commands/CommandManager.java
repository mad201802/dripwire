package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.SpawnCmd;

public class CommandManager {
    public static void registerCommands()
    {
        Dripwire.get().getCommand("spawn").setExecutor(new SpawnCmd());
        Dripwire.get().getCommand("ping").setExecutor(new PingCmd());
        Dripwire.get().getCommand("tpa").setExecutor(new TpaCmd());
        Dripwire.get().getCommand("tpaccept").setExecutor(new TpacceptCmd());
        Dripwire.get().getCommand("tpdeny").setExecutor(new TpdenyCmd());
        Dripwire.get().getCommand("tpcancel").setExecutor(new TpacancelCmd());
        Dripwire.get().getCommand("sethome").setExecutor(new SethomeCmd());
        Dripwire.get().getCommand("delhome").setExecutor(new DelhomeCmd());
        Dripwire.get().getCommand("home").setExecutor(new HomeCmd());
        Dripwire.get().getCommand("homes").setExecutor(new HomesCmd());
    }

}
