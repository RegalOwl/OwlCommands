package regalowl.basiccommands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Message implements CommandExecutor {
	
	private HashMap<String,String> lastMessaged = new HashMap<String,String>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.message") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		
		if (args.length >= 2) {
			Player messagee = Bukkit.getPlayer(args[0]);
			if (messagee == null) {
				sender.sendMessage(ChatColor.RED + "The specified player is not online.");
				return true;
			}
			String message = "";
			for (int i = 1; i < args.length; i++) {
				if (message.equals("")) {
					message = args[i];
				} else {
					message = message + " " + args[i];
				}
			}
			sender.sendMessage(ChatColor.GRAY + "[" + sender.getName() + "->" + messagee.getName() + "]" + message);
			messagee.sendMessage(ChatColor.GRAY + sender.getName() + " whispers: " + message);
			if (p != null) {
				lastMessaged.put(p.getName(), messagee.getName());
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Use /message [player] [message]");
		}
		return true;
	}
		
		
	public String getLastMessaged(String player) {
		if (lastMessaged.containsKey(player)) {
			return lastMessaged.get(player);
		} 
		return "";
	}

}
