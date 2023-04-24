package xymb;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import java.util.concurrent.TimeUnit;

public class StopGracefullyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {return true;}
        if (!(sender instanceof ConsoleCommandSender)) {return true;}

        int[] array = {1, 2, 3, 4, 5, 10, 20, 30, 45, 60, 75, 90, 120};

        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        for (int seconds: array) {
            int remaining = max - seconds;
            String message = "Server restarts in " + seconds + "s";

            Bukkit.getAsyncScheduler().runDelayed(StopGracefully.instance, task1 -> {
                        Bukkit.getServer().getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.RED + message));
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + message);
                }, remaining, TimeUnit.SECONDS);

        }
        Bukkit.getAsyncScheduler().runDelayed(StopGracefully.instance, task1 -> {
                Bukkit.shutdown();
            }, max, TimeUnit.SECONDS);
        return true;
    }
}
