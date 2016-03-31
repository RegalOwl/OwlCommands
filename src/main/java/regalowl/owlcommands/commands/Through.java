package regalowl.owlcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Through implements CommandExecutor {
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("owlcommands.through") && !p.hasPermission("owlcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		Location pl = p.getLocation();
		BlockFace direction = getDirection(pl.getYaw());
		Block cb = pl.getBlock().getRelative(direction);
		int c = 0;
		while (!cb.getType().isSolid()) {
			if (c > 64) {
				p.sendMessage(ChatColor.RED + "Could not find a suitable teleport location.");
				return true;
			}
			cb = cb.getRelative(direction);
			c++;
		}
		Block tb = cb.getRelative(direction);
		c = 0;
		while (tb.getType().isSolid() || tb.getRelative(BlockFace.UP).getType().isSolid()) {
			if (c > 64) {
				p.sendMessage(ChatColor.RED + "Could not find a suitable teleport location.");
				return true;
			}
			tb = tb.getRelative(direction);
			c++;
		}
		Location nl = tb.getLocation();
		nl.setX(nl.getBlockX() + .5);
		nl.setZ(nl.getBlockZ() + .5);
		nl.setYaw(p.getLocation().getYaw());
		nl.setPitch(p.getLocation().getPitch());
		p.teleport(nl);
		return true;
	}
	
	
	private BlockFace getDirection(Float yaw) {
	    int yawInt = Math.round(yaw / 90);
	    if (yawInt == -4 || yawInt == 0 || yaw == 4) {return BlockFace.SOUTH;}
	    if (yawInt == -1 || yawInt == 3) {return BlockFace.EAST;}
	    if (yawInt == -2 || yawInt == 2) {return BlockFace.NORTH;}
	    if (yawInt == -3 || yawInt == 1) {return BlockFace.WEST;}
	    return null;
	}
		
		

}
