package me.taylor.taylorStaff.listener.listeners;

import me.taylor.taylorStaff.listener.ListenerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryListener implements Listener {

    private ListenerHandler handler;

    public InventoryListener(ListenerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {

        Player player = (Player) e.getPlayer();

        if(handler.getPlugin().getManagerHandler().getFrozenManager().isFrozen(player.getUniqueId())) {

            //Cant reopen an inventory in the same tick as the InventoryCloseEvent so we wait 1 tick to open it.
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(handler.getPlugin().getManagerHandler().getInventoryManager().getFrozenInv());
                }
            }.runTaskLater(handler.getPlugin(), 1);

        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(handler.getPlugin().getManagerHandler().getFrozenManager().isFrozen(player.getUniqueId())) {

            e.setCancelled(true);

        }
    }
}
