package regalowl.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Heal implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.heal") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		
		if (args.length == 0) {
			if (p == null) {return true;}
			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setRemainingAir(300);
			sender.sendMessage(ChatColor.AQUA + "You have been healed.");
		}
		if (args.length == 1) {
			p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				sender.sendMessage(ChatColor.RED + "That player is not online.");
				return true;
			}
			p.setHealth(20.0);
			p.setFoodLevel(20);
			p.setRemainingAir(300);
			sender.sendMessage(ChatColor.AQUA + "The specified player's health has been restored.");
		}
		return true;
	}
		
		

}
