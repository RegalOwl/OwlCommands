package regalowl.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Time implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.time") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 1) {
			if (p == null) {return true;}
			long ticks = 0;
			if (args[0].equalsIgnoreCase("dawn")) {
				ticks = 0;
			} else if (args[0].equalsIgnoreCase("day")) {
				ticks = 6000L;
			} else if (args[0].equalsIgnoreCase("dusk")) {
				ticks = 12000L;
			} else if (args[0].equalsIgnoreCase("night")) {
				ticks = 18000L;
			} else {
				try {
					ticks = Integer.parseInt(args[0]);
				} catch (Exception e) {
					p.sendMessage(ChatColor.RED + "Use /time [ticks] or /time [dawn, dusk, day, or night]");
					return true;
				}
			}
			p.getWorld().setTime(ticks);
			p.sendMessage(ChatColor.AQUA + "Time set.");
		} else if (args.length == 2) {
			World w = Bukkit.getWorld(args[1]);
			if (w == null) {
				sender.sendMessage(ChatColor.RED + "Use /time [ticks] [world] or /time [dawn, dusk, day, or night] [world]");
				return true;
			}
			long ticks = 0;
			if (args[0].equalsIgnoreCase("dawn")) {
				ticks = 0;
			} else if (args[0].equalsIgnoreCase("day")) {
				ticks = 12000L;
			} else if (args[0].equalsIgnoreCase("dusk")) {
				
			} else if (args[0].equalsIgnoreCase("night")) {
				
			} else {
				try {
					ticks = Integer.parseInt(args[0]);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + "Use /time [ticks] [world] or /time [dawn, dusk, day, or night] [world]");
					return true;
				}
			}
			w.setTime(ticks);
			sender.sendMessage(ChatColor.AQUA + "Time set.");
		}
		
		return true;
	}
		
		

}
