package regalowl.owlcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import regalowl.owlcommands.OwlCommands;



public class Back implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.back") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		Location pl = OwlCommands.bc.getTeleport().getPriorLocation(p.getName());
		if (pl == null) {
			p.sendMessage(ChatColor.RED + "There is no location to return to.");
		} else {
			p.teleport(pl);
		}
		return true;
	}
		
		

}
