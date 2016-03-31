package regalowl.owlcommands.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Playerinfo implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.playerinfo") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 1) {
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
			if (!op.hasPlayedBefore()) {
				p.sendMessage(ChatColor.RED + "That player has not played on this server");
				return true;
			} else {
				sender.sendMessage(ChatColor.GREEN + "######## Player Info #######");
				sender.sendMessage(ChatColor.AQUA + "Name: " + op.getName());
				sender.sendMessage(ChatColor.AQUA + "First Played: " + getDateFromEpoch(op.getFirstPlayed()));
				sender.sendMessage(ChatColor.AQUA + "Last Played: " + getDateFromEpoch(op.getLastPlayed()));
				sender.sendMessage(ChatColor.AQUA + "Banned: " + op.isBanned());
				sender.sendMessage(ChatColor.AQUA + "Online: " + op.isOnline());
				sender.sendMessage(ChatColor.AQUA + "OP: " + op.isOp());
				sender.sendMessage(ChatColor.AQUA + "Whitelisted: " + op.isWhitelisted());
				if (op.isOnline()) {
					Player player = Bukkit.getPlayer(op.getName());
					if (player != null) {
						Location pl = player.getLocation();
						sender.sendMessage(ChatColor.AQUA + "Location: " + pl.getWorld().getName() + " " + pl.getBlockX() + "," + pl.getBlockY() + "," + pl.getBlockZ());
						sender.sendMessage(ChatColor.AQUA + "In Vehicle: " + player.isInsideVehicle());
						sender.sendMessage(ChatColor.AQUA + "Flying: " + player.isFlying());
						sender.sendMessage(ChatColor.AQUA + "Dead: " + player.isDead());
					}
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "Use /playerinfo [player]");
		}
		
		return true;
	}
	
	public String getDateFromEpoch(long epochTime) {
		Date date = new Date(epochTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		format.setTimeZone(TimeZone.getDefault());
		return format.format(date);
	}


}
