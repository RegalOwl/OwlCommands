package regalowl.basiccommands;

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



public class Ignore implements CommandExecutor, Listener {

	private StringFunctions sf;
	
	public Ignore() {
		sf = new StringFunctions();
		BasicCommands.bc.getServer().getPluginManager().registerEvents(this, BasicCommands.bc);
	}
	
	public void setIgnored(String player, ArrayList<String> ignored) {
		BasicCommands.y.players().set(player + ".ignore", sf.implode(ignored, ","));
	}
	public ArrayList<String> getIgnored(String player) {
		return sf.explode(BasicCommands.y.players().getString(player + ".ignore"), ",");
	}	
	public boolean hasIgnored(String player) {
		return BasicCommands.y.players().isSet(player + ".ignore");
	}


	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.ignore") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		if (args.length == 1) {
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
			if (op == null) {
				p.sendMessage(ChatColor.RED + "The specified player has not played on this server.");
				return true;
			}
			if (hasIgnored(p.getName())) {
				ArrayList<String> ignored = getIgnored(p.getName());
				if (ignored.contains(op.getName())) {
					ignored.remove(op.getName());
					setIgnored(p.getName(), ignored);
					p.sendMessage(ChatColor.AQUA + "Player unignored.");
				} else {
					ignored.add(op.getName());
					setIgnored(p.getName(), ignored);
					p.sendMessage(ChatColor.AQUA + "Player ignored.");
				}
			} else {
				ArrayList<String> ignored = new ArrayList<String>();
				ignored.add(op.getName());
				setIgnored(p.getName(), ignored);
				p.sendMessage(ChatColor.AQUA + "Player ignored.");
			}
		} else {
			p.sendMessage(ChatColor.RED + "Use /ignore [name]");
		}
		return true;
	}
		
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		String mSender = event.getPlayer().getName();
		boolean cancel = false;
		for (Player p : event.getRecipients()) {
			if (hasIgnored(p.getName())) {
				ArrayList<String> ignored = getIgnored(p.getName());
				if (ignored.contains(mSender)) {
					cancel = true;
					break;
				}
			}
		}
		if (cancel) {
			event.setCancelled(true);
			for (Player p : event.getRecipients()) {
				if (hasIgnored(p.getName())) {
					ArrayList<String> ignored = getIgnored(p.getName());
					if (!ignored.contains(mSender)) {
						p.sendMessage(message);
					}
				}
			}
		}
	}
	


}
