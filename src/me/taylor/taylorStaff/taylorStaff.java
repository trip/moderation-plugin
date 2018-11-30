package me.taylor.taylorStaff;

import me.taylor.taylorStaff.commands.*;
import me.taylor.taylorStaff.listener.ListenerHandler;
import me.taylor.taylorStaff.listener.listeners.PlayerJoin;
import me.taylor.taylorStaff.manager.ManagerHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by taylor on 01/04/2017.
 */

public class taylorStaff extends JavaPlugin {

    private ManagerHandler managerHandler;

    @Override
    public void onEnable() {
        new ListenerHandler(this);
        new PlayerJoin(this);
        managerHandler = new ManagerHandler(this);
        getCommand("helpop").setExecutor(new HelpOPCommand(this));
        getCommand("report").setExecutor(new ReportCommand(this));
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("kick").setExecutor(new KickCommand(this));
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public ManagerHandler getManagerHandler() {
        return managerHandler;
    }

}
