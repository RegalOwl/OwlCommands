package regalowl.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Weather implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.weather") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		World w = null;
		if (args.length == 1) {
			if (p == null) {return true;}
			w = p.getWorld();
		}
		if (args.length == 2) {
			w = Bukkit.getWorld(args[1]);
			if (w == null) {
				p.sendMessage(ChatColor.RED + "Use /weather [sun or storm] [world]");
				return true;
			}
		}
		if (args[0].equalsIgnoreCase("sun")) {
			w.setStorm(false);
			w.setThundering(false);
			p.sendMessage(ChatColor.AQUA + "Weather set to sun.");
		} else if (args[0].equalsIgnoreCase("storm")) {
			w.setStorm(true);
			w.setThundering(true);
			p.sendMessage(ChatColor.AQUA + "Weather set to storm.");
		} else {
			p.sendMessage(ChatColor.RED + "Use /weather [sun or storm]");
		}

		return true;
	}
		
		

}
