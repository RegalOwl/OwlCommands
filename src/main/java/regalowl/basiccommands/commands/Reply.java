package regalowl.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import regalowl.basiccommands.BasicCommands;



public class Reply implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.reply") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		if (args.length >= 1) {
			Player lm = Bukkit.getPlayer(BasicCommands.bc.getMessage().getLastMessaged(p.getName()));
			if (lm == null) {
				sender.sendMessage(ChatColor.RED + "There is no one to reply to.");
				return true;
			} else {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					if (message.equals("")) {
						message = args[i];
					} else {
						message = message + " " + args[i];
					}
				}
				sender.sendMessage(ChatColor.GRAY + "[" + sender.getName() + "->" + lm.getName() + "]" + message);
				lm.sendMessage(ChatColor.GRAY + sender.getName() + " whispers: " + message);
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Use /reply [message]");
		}
		return true;
	}
		
		

}
