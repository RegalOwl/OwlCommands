package regalowl.owlcommands;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import regalowl.owlcommands.commands.Back;
import regalowl.owlcommands.commands.Flyspeed;
import regalowl.owlcommands.commands.Gamemode;
import regalowl.owlcommands.commands.God;
import regalowl.owlcommands.commands.Heal;
import regalowl.owlcommands.commands.Ignore;
import regalowl.owlcommands.commands.Ip;
import regalowl.owlcommands.commands.Message;
import regalowl.owlcommands.commands.Mute;
import regalowl.owlcommands.commands.Nick;
import regalowl.owlcommands.commands.Playerinfo;
import regalowl.owlcommands.commands.Pvp;
import regalowl.owlcommands.commands.Ram;
import regalowl.owlcommands.commands.Reply;
import regalowl.owlcommands.commands.Teleport;
import regalowl.owlcommands.commands.Through;
import regalowl.owlcommands.commands.Time;
import regalowl.owlcommands.commands.Top;
import regalowl.owlcommands.commands.Weather;




public class OwlCommands extends JavaPlugin implements Listener {
	public static OwlCommands bc;
	public static YamlFile y;
	
	private Message message;
	private Teleport teleport;
	
	@Override
	public void onEnable() {
		bc = this;
		y = new YamlFile();

		message = new Message();
		teleport = new Teleport();

		Bukkit.getServer().getPluginCommand("back").setExecutor(new Back());
		Bukkit.getServer().getPluginCommand("gamemode").setExecutor(new Gamemode());
		Bukkit.getServer().getPluginCommand("flyspeed").setExecutor(new Flyspeed());
		Bukkit.getServer().getPluginCommand("god").setExecutor(new God());
		Bukkit.getServer().getPluginCommand("heal").setExecutor(new Heal());
		Bukkit.getServer().getPluginCommand("ignore").setExecutor(new Ignore());
		Bukkit.getServer().getPluginCommand("ip").setExecutor(new Ip());
		Bukkit.getServer().getPluginCommand("message").setExecutor(message);
		Bukkit.getServer().getPluginCommand("reply").setExecutor(new Reply());
		Bukkit.getServer().getPluginCommand("mute").setExecutor(new Mute());
		Bukkit.getServer().getPluginCommand("nick").setExecutor(new Nick());
		Bukkit.getServer().getPluginCommand("playerinfo").setExecutor(new Playerinfo());
		Bukkit.getServer().getPluginCommand("pvp").setExecutor(new Pvp());
		Bukkit.getServer().getPluginCommand("ram").setExecutor(new Ram());
		Bukkit.getServer().getPluginCommand("teleport").setExecutor(teleport);
		Bukkit.getServer().getPluginCommand("time").setExecutor(new Time());
		Bukkit.getServer().getPluginCommand("weather").setExecutor(new Weather());
		Bukkit.getServer().getPluginCommand("through").setExecutor(new Through());
		Bukkit.getServer().getPluginCommand("top").setExecutor(new Top());
	}
	
	
	@Override
	public void onDisable() {
		y.saveYamls();
	}


	public Message getMessage() {
		return message;
	}
	
	public Teleport getTeleport() {
		return teleport;
	}

}
