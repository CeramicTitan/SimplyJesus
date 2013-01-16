package me.ceramictitan.simplyjesus.Commands;

import me.ceramictitan.simplyjesus.User.User;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGod implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("jesus")) {
				if (args.length == 1) {
					if (sender.getServer().getPlayer(args[0]) != null) {
						Player targetplayer = sender.getServer().getPlayer(
								args[0]);
						User.enableGodMode(targetplayer);
						sender.sendMessage(ChatColor.AQUA
								+ targetplayer.getDisplayName()
								+ " Your god mode is now active!");
						targetplayer.sendMessage(ChatColor.GOLD + "["
								+ sender.getName() + "]" + ChatColor.AQUA
								+ " has granted you god mode!");
					} else {
						sender.sendMessage(ChatColor.RED
								+ "Can't find that player, maybe they're offline?");
					}
				} else if (args.length > 1) {
					sender.sendMessage(ChatColor.RED + "Too many arguments!");
					sender.sendMessage(ChatColor.GREEN + "Usage: /"
							+ commandLabel + " <player>");
				}
			}
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("jesus")) {
			if (!player.hasPermission("simply.jesus")) {
				player.sendMessage(ChatColor.RED + "No Permission!");
				return true;
			}
			if (args.length == 0) {
				User.enableGodMode((Player) sender);
				player.sendMessage(ChatColor.AQUA
						+ "Your god mode is now active!");

			} else if (args.length == 1) {
				if (!player.hasPermission("simply.jesus.others")) {
					player.sendMessage(ChatColor.RED + "No Permission!");
					return true;
				}
				if (player.getServer().getPlayer(args[0]) != null) {
					Player targetplayer = player.getServer().getPlayer(args[0]);
					User.enableGodMode((Player) targetplayer);
					player.sendMessage(ChatColor.AQUA
							+ "jesus mode now active for " + ChatColor.GOLD
							+ targetplayer.getDisplayName());
					targetplayer.sendMessage(ChatColor.GOLD + "["
							+ sender.getName() + "]" + ChatColor.AQUA
							+ " has granted you god mode!");
				} else {
					player.sendMessage(ChatColor.RED
							+ "Can't find that player, maybe they're offline?");
				}
			} else if (args.length > 1 || args.length < 1) {
				player.sendMessage(ChatColor.RED + "Error!");
				player.sendMessage(ChatColor.GREEN + "Usage: /" + commandLabel
						+ " [player]");
			}
		}
		return true;
	}
}