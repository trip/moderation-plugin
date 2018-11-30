package me.taylor.taylorStaff.commands;

import me.taylor.taylorStaff.taylorStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by taylor on 06/04/2017.
 */

public class HelpOPCommand implements CommandExecutor {

    private taylorStaff plugin;
    public final String prefix = ChatColor.GOLD + "[Request]" + ChatColor.RESET;

    public HelpOPCommand(taylorStaff plugin) {
        this.plugin = plugin;
        plugin.getCommand("helpop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("helpop")) {
            if (args.length == 0) {
                sender.sendMessage(this.prefix + ChatColor.RED + " Invalid Arguments!");
                return true;
            } else {

                StringBuilder x = new StringBuilder();

                for (int i = 0; i < args.length; i++) {
                    x.append(args[i] + " ");
                }

                // Staff Request Receive
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.hasPermission("moderation.request.receive")) {
                        player.sendMessage(this.prefix + ChatColor.WHITE + " " +sender.getName() + ChatColor.GOLD + " » " + ChatColor.WHITE + x.toString());
                    }

                }

                sender.sendMessage(this.prefix + ChatColor.GREEN + " Your request has been sent!");
                return true;
            }
        }
        return true;
    }
}
