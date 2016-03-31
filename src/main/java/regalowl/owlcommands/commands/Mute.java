package regalowl.owlcommands.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import regalowl.owlcommands.OwlCommands;
import regalowl.owlcommands.StringFunctions;



public class Mute implements CommandExecutor, Listener {

	private StringFunctions sf;
	
	public Mute() {
		sf = new StringFunctions();
		OwlCommands.bc.getServer().getPluginManager().registerEvents(this, OwlCommands.bc);
	}
	
	public ArrayList<String> getMuted() {
		return sf.explode(OwlCommands.y.config().getString("muted"), ",");
	}
	public void setMuted(ArrayList<String> muted) {
		OwlCommands.y.config().set("muted", sf.implode(muted, ","));
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.mute") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 1) {
			OfflinePlayer mute = Bukkit.getOfflinePlayer(args[0]);
			if (mute != null) {
				ArrayList<String> muted = getMuted();
				if (muted.contains(mute.getName())) {
					muted.remove(mute.getName());
					setMuted(muted);
					sender.sendMessage(ChatColor.AQUA + "Player unmuted.");
				} else {
					muted.add(mute.getName());
					setMuted(muted);
					sender.sendMessage(ChatColor.AQUA + "Player muted.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "The specified player has not played on this server.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Use /mute [player]");
		}
		
		return true;
	}
		
		
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String mSender = event.getPlayer().getName();
		if (getMuted().contains(mSender)) {
			event.setCancelled(true);
		}
	}
	

}
