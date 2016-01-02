package regalowl.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.gamemode") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 0) {
			if (p == null) {return true;}
			if (p.getGameMode() == GameMode.SURVIVAL) {
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage(ChatColor.AQUA + "You are now in CREATIVE mode.");
			} else {
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage(ChatColor.AQUA + "You are now in SURVIVAL mode.");
			}
		} else if (args.length == 1) {
			if (p == null) {return true;}
			
			try {
				GameMode gm = GameMode.getByValue(Integer.parseInt(args[0])); 
				p.setGameMode(gm);
				p.sendMessage(ChatColor.AQUA + "You are now in "+gm.name()+" mode.");
			} catch (Exception e) {
				p.sendMessage(ChatColor.RED + "Use /gamemode [0, 1, or 2]");
			}
		} else if (args.length == 2) {
			GameMode gm = null;
			try {
				gm = GameMode.getByValue(Integer.parseInt(args[0])); 
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "Use /gamemode [0, 1, or 2] [player]");
			}
			Player ap = Bukkit.getPlayer(args[1]);
			if (ap == null) {
				sender.sendMessage(ChatColor.RED + "The specified player does not exist or is offline.");
			}
			
			ap.setGameMode(gm);
			p.sendMessage(ChatColor.AQUA + ap.getName() + " is now in "+gm.name()+" mode.");
		}
		
		
		return true;
	}
}
