package regalowl.owlcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Top implements CommandExecutor {
	

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
		Block cb = pl.getBlock().getRelative(BlockFace.UP);
		while (!cb.getType().isSolid()) {
			cb = cb.getRelative(BlockFace.UP);
		}
		Block tb = cb.getRelative(BlockFace.UP);
		while (tb.getType().isSolid() || tb.getRelative(BlockFace.UP).getType().isSolid()) {
			if (tb.getLocation().getBlockY() == 255) {
				break;
			}
			tb = tb.getRelative(BlockFace.UP);
		}
		Location nl = tb.getLocation();
		nl.setX(nl.getBlockX() + .5);
		nl.setZ(nl.getBlockZ() + .5);
		nl.setYaw(p.getLocation().getYaw());
		nl.setPitch(p.getLocation().getPitch());
		p.teleport(nl);
		return true;
	}
	
		
		

}
