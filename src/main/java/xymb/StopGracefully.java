package xymb;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class StopGracefully extends JavaPlugin {

    public static StopGracefully instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("stopgracefully").setExecutor(new StopGracefullyCommand());
    }

    
}
