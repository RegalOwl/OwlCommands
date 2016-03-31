package regalowl.owlcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Flyspeed implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.flyspeed") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 1) {
			if (p == null) {return true;}
			try {
				float speed = Float.parseFloat(args[0])/100f; 
				if (speed > 1f) {
					speed = 1f;
				}
				if (speed < 0f) {
					speed = 0f;
				}
				p.setFlySpeed(speed);
				p.sendMessage(ChatColor.AQUA + "Fly speed set");
			} catch (Exception e) {
				p.sendMessage(ChatColor.RED + "Use /flyspeed [speed]");
			}
		}
		
		
		return true;
	}
}
