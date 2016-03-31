package regalowl.owlcommands.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import regalowl.owlcommands.OwlCommands;



public class God implements CommandExecutor, Listener {
	
	private ArrayList<String> godMode = new ArrayList<String>();
	
	public God() {
		OwlCommands.bc.getServer().getPluginManager().registerEvents(this, OwlCommands.bc);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.god") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		if (godMode.contains(p.getName())) {
			godMode.remove(p.getName());
			p.sendMessage(ChatColor.AQUA + "God mode disabled.");
		} else {
			godMode.add(p.getName());
			p.sendMessage(ChatColor.AQUA + "God mode enabled.");
		}
		
		return true;
	}
		
		
	@EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (godMode.contains(player.getName())) {
				event.setCancelled(true);
			}
		}
	}
}
