package regalowl.basiccommands;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;


public class Pvp implements CommandExecutor, Listener {

	private HashMap<String, Cooldown> onCooldown = new HashMap<String, Cooldown>();
	
	
	public Pvp() {
		for (Player p:Bukkit.getOnlinePlayers()) {
			if (!pvpSet(p.getName())) {
				setPvp(p.getName(), false);
			}
		}
		BasicCommands.bc.getServer().getPluginManager().registerEvents(this, BasicCommands.bc);
	}
	
	
	public void setPvp(String player, boolean status) {
		BasicCommands.y.players().set(player + ".pvp", status);
	}
	public boolean getPvp(String player) {
		return BasicCommands.y.players().getBoolean(player + ".pvp");
	}
	public boolean pvpSet(String player) {
		return BasicCommands.y.players().isSet(player + ".pvp");
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = null;
		if (sender instanceof Player) {
			p = (Player)sender;
			if (!p.hasPermission("bcommands.pvp") && !p.hasPermission("bcommands.admin")) {
				p.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
		}
		if (p == null) {return true;}
		if (onCooldown.containsKey(p.getName())) {
			p.sendMessage(ChatColor.RED + "You were recently in combat and cannot disable PvP for " + onCooldown.get(p.getName()).getRemainingSeconds() + " more seconds.");
			return true;
		}
		if (!pvpSet(p.getName())) {
			setPvp(p.getName(), true);
			BasicCommands.y.players().set(p.getName() + ".pvp", true);
			p.sendMessage(ChatColor.AQUA + "Pvp is now enabled.");
		} else {
			boolean state = getPvp(p.getName());
			if (state) {
				setPvp(p.getName(), false);
				BasicCommands.y.players().set(p.getName() + ".pvp", false);
				p.sendMessage(ChatColor.AQUA + "Pvp is now disabled.");
			} else {
				setPvp(p.getName(), true);
				BasicCommands.y.players().set(p.getName() + ".pvp", true);
				p.sendMessage(ChatColor.AQUA + "Pvp is now enabled.");
			}
		}
		return true;
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
		if (!pvpSet(event.getPlayer().getName())) {
			setPvp(event.getPlayer().getName(), false);
		}
    }
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
		if (onCooldown.containsKey(event.getPlayer().getName())) {
			onCooldown.get(event.getPlayer().getName()).getTask().cancel();
			onCooldown.remove(event.getPlayer().getName());
			if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				event.getPlayer().setHealth(0.0);
			}
		}
	}
	
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    	if (!(event.getEntity() instanceof Player)) {return;}
    	Player damager = null;
        if (event.getDamager() instanceof Player) {
        	damager = (Player)event.getDamager();
        } else if (event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
        	damager = (Player)((Projectile) event.getDamager()).getShooter();
        }
        if (damager == null) {return;}
    	Player damaged = (Player)event.getEntity();
    	if (!(getPvp(damager.getName()) && getPvp(damaged.getName()))) {
    		event.setCancelled(true);
    		return;
    	}
    	if (onCooldown.containsKey(damaged.getName())) {
    		onCooldown.get(damaged.getName()).cancelTask();
    		onCooldown.remove(damaged.getName());
    	}
    	Cooldown cd = new Cooldown(damaged.getName());
    	onCooldown.put(damaged.getName(), cd);
    }	
    
    private class Cooldown {
    	private String p;
    	private BukkitTask t;
    	private int seconds;
    	private int waitSeconds;
    	Cooldown(String player) {
    		seconds = 0;
    		waitSeconds = 120;
    		p = player;
    		t = BasicCommands.bc.getServer().getScheduler().runTaskTimer(BasicCommands.bc, new Runnable() {
    			public void run() {
    				seconds++;
    				if (seconds >= waitSeconds) {
        				onCooldown.remove(p);
        				t.cancel();
    				}
    			}
    		}, 20L, 20L);
    	}
    	public BukkitTask getTask() {return t;}
    	public void cancelTask() {if (t != null) {t.cancel();}}
    	public int getRemainingSeconds() {return (waitSeconds - seconds);}
    }

}
