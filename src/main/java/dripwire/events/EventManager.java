package dripwire.events;

import dripwire.Dripwire;
import dripwire.events.listeners.PlayerJoinListener;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class EventManager {
    private final ArrayList<Listener> events = new ArrayList<>();

    public EventManager() {
        registerEvents();
        init();
    }

    private void init() {
        events.forEach(event -> Dripwire.get().getServer().getPluginManager().registerEvents(event, Dripwire.get()));
    }

    private void registerEvents() {
        events.add(new PlayerJoinListener());
    }

}
