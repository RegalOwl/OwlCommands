package regalowl.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Ip implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.ip") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 0) {
			if (p == null) {
				return true;
			}
			sender.sendMessage(ChatColor.AQUA + "Your IP is: " + p.getAddress().getAddress().toString().replace("/", ""));
		} else if (args.length == 1) {
			Player tp = Bukkit.getPlayer(args[0]);
			if (tp == null) {
				sender.sendMessage(ChatColor.RED + "The specified player is not online.");
			} else {
				sender.sendMessage(ChatColor.AQUA + tp.getName() + "'s IP is: " + tp.getAddress().getAddress().toString());
			}
		}
		
		return true;
	}
		
		

}
