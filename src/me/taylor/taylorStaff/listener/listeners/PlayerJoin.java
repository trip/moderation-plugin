package me.taylor.taylorStaff.listener.listeners;

import me.taylor.taylorStaff.taylorStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by taylor on 07/04/2017.
 */

public class PlayerJoin implements Listener {

    private taylorStaff plugin;

    public PlayerJoin(taylorStaff plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (plugin.getConfig().getConfigurationSection("banned_players") != null) {
            for (String section : plugin.getConfig().getConfigurationSection("banned_players").getKeys(false)) {
                if (section.equals(player.getUniqueId().toString())) {
                    player.kickPlayer(ChatColor.RED + "You are banned from the server!\nBy: " + plugin.getConfig().getString("banned_players." + player.getUniqueId().toString() + ".banner") + "\nReason: " + plugin.getConfig().getString("banned_players." + player.getUniqueId().toString() + ".reason"));
                }
            }
        }
    }

}