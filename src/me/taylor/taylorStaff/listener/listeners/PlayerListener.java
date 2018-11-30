package me.taylor.taylorStaff.listener.listeners;

import me.taylor.taylorStaff.listener.ListenerHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private ListenerHandler handler;
    public PlayerListener(ListenerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (handler.getPlugin().getManagerHandler().getFrozenManager().isFrozen(player.getUniqueId())) {
            handler.getPlugin().getServer().broadcast(ChatColor.GOLD + player.getName() + " logged out while being frozen", "request.freeze");
            handler.getPlugin().getManagerHandler().getFrozenManager().unfreezeUUID(player.getUniqueId());
            handler.getPlugin().getManagerHandler().getPlayerSnapshotManager().restorePlayer(player);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if(handler.getPlugin().getManagerHandler().getFrozenManager().isFrozen(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }
}
