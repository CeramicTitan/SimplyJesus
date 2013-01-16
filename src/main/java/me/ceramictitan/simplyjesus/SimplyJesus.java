package me.ceramictitan.simplyjesus;

import java.io.IOException;

import me.ceramictitan.simplyjesus.Commands.CommandGod;
import me.ceramictitan.simplyjesus.Commands.CommandUngod;
import me.ceramictitan.simplyjesus.Listeners.MyPlayerListener;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplyJesus extends JavaPlugin {
	public final MyPlayerListener playerListener = new MyPlayerListener();

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(pdfFile.getName() + " is now disabled.");

	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(
				pdfFile.getName() + " Version " + pdfFile.getVersion()
						+ " is now enabled.");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.playerListener, this);
		getConfig().addDefault("metrics.enable", true);
		getConfig().options().copyDefaults(true);
		saveConfig();
		startMetrics();
		getCommands();
	}

	public void getCommands() {
		getCommand("jesus", new CommandGod());
		getCommand("unjesus", new CommandUngod());
		getLogger().info("Installing Commands");

	}

	public void getCommand(String command, CommandExecutor commandexecutor) {
		Bukkit.getServer().getPluginCommand(command)
				.setExecutor(commandexecutor);
	}

	private void startMetrics() {
		if (getConfig().getBoolean("metrics.enable")) {
			try {
				getLogger().info("Metrics is starting");
				MetricsLite metrics = new MetricsLite(this);
				metrics.start();
			} catch (IOException e) {
				// Failed to submit the stats :-(
			}
		}
	}
}
