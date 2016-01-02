package regalowl.basiccommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Ram implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.ram") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
        Runtime runtime = Runtime.getRuntime();
        sender.sendMessage(ChatColor.GREEN + "######## Heap Statistics ########");
        sender.sendMessage(ChatColor.AQUA + "Used Memory: "+ (runtime.totalMemory() - runtime.freeMemory())/1048576 + " MB");
        sender.sendMessage(ChatColor.AQUA + "Free Memory: "+ runtime.freeMemory()/1048576 + " MB");
        sender.sendMessage(ChatColor.AQUA + "Heap Size: " + runtime.totalMemory()/1048576 + " MB");
        sender.sendMessage(ChatColor.AQUA + "Maximum Heap Size: " + runtime.maxMemory()/1048576 + " MB");
        sender.sendMessage(ChatColor.AQUA + "Total Free Memory: " + (runtime.maxMemory()/1048576 - (runtime.totalMemory() - runtime.freeMemory())/1048576) + " MB");
		return true;
	}
		
		

}
