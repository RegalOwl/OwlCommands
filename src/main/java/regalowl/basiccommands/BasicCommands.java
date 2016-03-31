package regalowl.basiccommands;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import regalowl.basiccommands.commands.Back;
import regalowl.basiccommands.commands.Flyspeed;
import regalowl.basiccommands.commands.Gamemode;
import regalowl.basiccommands.commands.God;
import regalowl.basiccommands.commands.Heal;
import regalowl.basiccommands.commands.Ignore;
import regalowl.basiccommands.commands.Ip;
import regalowl.basiccommands.commands.Message;
import regalowl.basiccommands.commands.Mute;
import regalowl.basiccommands.commands.Nick;
import regalowl.basiccommands.commands.Playerinfo;
import regalowl.basiccommands.commands.Pvp;
import regalowl.basiccommands.commands.Ram;
import regalowl.basiccommands.commands.Reply;
import regalowl.basiccommands.commands.Teleport;
import regalowl.basiccommands.commands.Through;
import regalowl.basiccommands.commands.Time;
import regalowl.basiccommands.commands.Top;
import regalowl.basiccommands.commands.Weather;




public class BasicCommands extends JavaPlugin implements Listener {
	public static BasicCommands bc;
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
