package me.taylor.taylorStaff.commands;

import me.taylor.taylorStaff.taylorStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by taylor on 07/04/2017.
 */

public class KickCommand implements CommandExecutor {

    private taylorStaff plugin;
    public final String prefix = ChatColor.GOLD + "[Kick] " + ChatColor.RESET;

    public KickCommand(taylorStaff plugin) {
        this.plugin = plugin;
        plugin.getCommand("kick").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // /kick taylor dot

        if (label.equalsIgnoreCase("kick")) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.hasPermission("kick.kick")) {
                    if (args.length == 0) {
                        sender.sendMessage(this.prefix + ChatColor.RED + "Invalid Arguments!");
                    } else if (args.length == 1) {
                        sender.sendMessage(this.prefix + ChatColor.RED + "Invalid Arguments!");
                    } else {
                        Player target = Bukkit.getPlayer(args[0]);

                        if (target == null) {
                            sender.sendMessage(ChatColor.RED + args[0] + " is not online!");
                            return true;
                        }

                        StringBuilder x = new StringBuilder();

                        for (int i = 1; i < args.length; i++) {
                            x.append(args[i] + " ");
                        }

                        String kicker = "Server";

                        if (sender instanceof Player) {
                            kicker = sender.getName();
                        }

                        target.kickPlayer(ChatColor.RED + "You have been kicked from the serve by: " + kicker + "\nfor " + x.toString().trim());
                        sender.sendMessage(ChatColor.GRAY + "(SILENT)" + ChatColor.GREEN + target.getName() + " was kicked from the server!");
                    }


                }
            }
        }
        return true;
    }
}
