package me.taylor.taylorStaff.commands;

import me.taylor.taylorStaff.taylorStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

public class FreezeCommand implements CommandExecutor {

    private taylorStaff plugin;
    public FreezeCommand(taylorStaff plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!sender.hasPermission("moderation.freeze.freeze")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }

        if(args.length != 1)
            return false;

        Player target = plugin.getServer().getPlayer(args[0]);

        if(target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if(plugin.getManagerHandler().getFrozenManager().isFrozen(target.getUniqueId())) {
            sender.sendMessage(ChatColor.GREEN + "You unfroze " + target.getName());

            plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been unfrozen by " + ChatColor.RED +
                    (sender instanceof Player ? sender.getName() : "Console"), "freeze.freeze");

            unfreezePlayer(target);
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "You froze " + target.getName());

        plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been frozen by " + ChatColor.RED +
                (sender instanceof Player ? sender.getName() : "Console"), "freeze.freeze");

        freezePlayer(target);
        return true;
    }

    private void freezePlayer(Player player) {
        plugin.getManagerHandler().getPlayerSnapshotManager().takeSnapshot(player);
        plugin.getManagerHandler().getFrozenManager().freezeUUID(player.getUniqueId());
        player.sendMessage(ChatColor.RED + "YOU HAVE BEEN FROZEN BY A STAFF MEMBER, LOGGING OUT WILL RESULT IN A BAN!");
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setWalkSpeed(0.0F);
        clearPotionEffects(player);
        player.updateInventory();
        player.openInventory(plugin.getManagerHandler().getInventoryManager().getFrozenInv());
    }

    private void unfreezePlayer(Player player) {
        plugin.getManagerHandler().getFrozenManager().unfreezeUUID(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You have been unfrozen by a staff member.");
        player.closeInventory();
        plugin.getManagerHandler().getPlayerSnapshotManager().restorePlayer(player);
    }

    private void clearPotionEffects(Player player) {
        for(PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
