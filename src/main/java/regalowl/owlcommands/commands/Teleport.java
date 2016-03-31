package regalowl.owlcommands.commands;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Teleport implements CommandExecutor {
	
	private HashMap<String, Location> priorLocations = new HashMap<String, Location>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.teleport") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("spawn")) {
				if (p == null) {
					sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
					return true;
				}
				priorLocations.put(p.getName(), newLoc(p.getLocation()));
				p.teleport(newLoc(p.getWorld().getSpawnLocation()));
			} else {
				Player tp = Bukkit.getPlayer(args[0]);
				if (p == null || tp == null) {
					sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
					return true;
				}
				priorLocations.put(p.getName(), newLoc(p.getLocation()));
				p.teleport(newLoc(tp.getLocation()));
			}
		} else if (args.length == 2) {
			Player tp1 = Bukkit.getPlayer(args[0]);
			Player tp2 = Bukkit.getPlayer(args[1]);
			if (tp1 == null || tp2 == null) {
				sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
				return true;
			}
			priorLocations.put(tp1.getName(), newLoc(tp1.getLocation()));
			tp1.teleport(newLoc(tp2.getLocation()));
		} else if (args.length == 3) {
			if (p == null) {
				sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
				return true;
			}
			try {
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				Location pl = newLoc(p.getLocation());
				pl.setX(x);
				pl.setY(y);
				pl.setZ(z);
				pl.getChunk().load(true);
				priorLocations.put(p.getName(), newLoc(p.getLocation()));
				p.teleport(pl);
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
				return true;
			}
		} else if (args.length == 4) {
			if (p == null) {
				sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
				return true;
			}
			try {
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				int z = Integer.parseInt(args[2]);
				World w = Bukkit.getWorld(args[3]);
				if (w == null) {
					sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
					return true;
				}
				Location pl = newLoc(p.getLocation());
				pl.setX(x);
				pl.setY(y);
				pl.setZ(z);
				pl.setWorld(w);
				pl.getChunk().load(true);
				priorLocations.put(p.getName(), newLoc(p.getLocation()));
				p.teleport(pl);
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "Use /teleport [Player] (Player) or /teleport [x] [y] [z] (world) or /teleport spawn");
				return true;
			}
		}
		
		
		return true;
	}
	
	public Location newLoc(Location l) {
		Location nl = new Location (l.getWorld(), l.getX(), l.getY(), l.getZ());
		nl.setPitch(l.getPitch());
		nl.setYaw(l.getYaw());
		return nl;
	}
		
	public Location getPriorLocation(String player) {
		if (priorLocations.containsKey(player)) {
			return priorLocations.get(player);
		} else {
			return null;
		}
	}

}
