package me.taylor.taylorStaff.commands;

import me.taylor.taylorStaff.taylorStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by taylor on 28/03/2017.
 */

public class ReportCommand implements CommandExecutor {

    private taylorStaff plugin;
    public final String prefix = ChatColor.GOLD + "[Report]" + ChatColor.RESET;

    public ReportCommand(taylorStaff plugin) {
        this.plugin = plugin;
        plugin.getCommand("report").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("report")) {
            if (args.length == 0) {
                sender.sendMessage(this.prefix + ChatColor.RED + " Invalid Arguments!");
                return true;
            }
            // Preventing players from reporting themselves
            if (args[0].equalsIgnoreCase(sender.getName())) {
                sender.sendMessage(this.prefix + ChatColor.RED + " You can't report yourself!");
                return true;

            }
            Player target = Bukkit.getPlayer(args[0]);

            // Preventing players from reporting offline players
            if (target == null) {
                sender.sendMessage(this.prefix + ChatColor.RED + " " + args[0] + " is not online!");
                return true;
            } else {

                    StringBuilder x = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        x.append(args[i] + " ");
                    }

                    // Staff Report Recieve
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission("report.receive")) {
                            String reported = args[0];
                            player.sendMessage(ChatColor.GOLD + "[Report] " + ChatColor.WHITE +  reported + ChatColor.GRAY + " was reported by " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " for " +  ChatColor.WHITE + x.toString());
                        }

                    }

                    sender.sendMessage(this.prefix + ChatColor.GREEN + " Your report has been sent!");
                    return true;
                }
            }
            return true;
        }
    }