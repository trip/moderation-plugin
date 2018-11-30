package me.taylor.taylorStaff.listener;

import me.taylor.taylorStaff.taylorStaff;
import me.taylor.taylorStaff.listener.listeners.EntityListener;
import me.taylor.taylorStaff.listener.listeners.InventoryListener;
import me.taylor.taylorStaff.listener.listeners.PlayerListener;
import org.bukkit.event.Listener;

public class ListenerHandler {

    private taylorStaff plugin;
    public ListenerHandler(taylorStaff plugin) {
        this.plugin = plugin;
        loadListeners();
    }

    private Listener[] listeners = {
            new EntityListener(this),
            new InventoryListener(this),
            new PlayerListener(this)
    };

    private void loadListeners() {
        for(Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public taylorStaff getPlugin() {
        return plugin;
    }

}
