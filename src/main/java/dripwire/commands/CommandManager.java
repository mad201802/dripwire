package dripwire.commands;

import dripwire.Dripwire;
import dripwire.commands.cmd.*;
import dripwire.commands.cmd.home.DelhomeCmd;
import dripwire.commands.cmd.home.HomeCmd;
import dripwire.commands.cmd.home.HomesCmd;
import dripwire.commands.cmd.home.SethomeCmd;
import dripwire.commands.cmd.nick.NickCmd;
import dripwire.commands.cmd.tpa.TpaCmd;
import dripwire.commands.cmd.tpa.TpacancelCmd;
import dripwire.commands.cmd.tpa.TpacceptCmd;
import dripwire.commands.cmd.tpa.TpdenyCmd;
import dripwire.commands.cmd.warp.DelwarpCmd;
import dripwire.commands.cmd.warp.SetwarpCmd;
import dripwire.commands.cmd.warp.WarpCmd;
import dripwire.commands.cmd.warp.WarpsCmd;
import dripwire.commands.cmd.worlds.FarmweltCmd;

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
        Dripwire.get().getCommand("setwarp").setExecutor(new SetwarpCmd());
        Dripwire.get().getCommand("delwarp").setExecutor(new DelwarpCmd());
        Dripwire.get().getCommand("warps").setExecutor(new WarpsCmd());
        Dripwire.get().getCommand("warp").setExecutor(new WarpCmd());
        Dripwire.get().getCommand("homes").setExecutor(new HomesCmd());
        Dripwire.get().getCommand("nick").setExecutor(new NickCmd());
        Dripwire.get().getCommand("unnick").setExecutor(new NickCmd());
        Dripwire.get().getCommand("trash").setExecutor(new TrashCmd());
        Dripwire.get().getCommand("farmwelt").setExecutor(new FarmweltCmd());
    }

}
