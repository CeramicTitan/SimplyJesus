package me.ceramictitan.simplyjesus.Listeners;

import me.ceramictitan.simplyjesus.SimplyJesus;
import me.ceramictitan.simplyjesus.User.User;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyPlayerListener implements Listener {

	private SimplyJesus plugin;

	public MyPlayerListener(SimplyJesus sg) {
		plugin = sg;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("main.motd")) {
			String prefix = ChatColor.GOLD + "[SG] ";
			player.sendMessage(prefix + ChatColor.YELLOW
					+ "This server is Running version " + ChatColor.DARK_AQUA
					+ plugin.getDescription().getVersion() + ChatColor.YELLOW
					+ " of " + plugin.getDescription().getName());
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = ((Player) event.getEntity());
			;
			if (User.godlist.containsKey(player.getName())) {
				event.setCancelled(true);
				player.playEffect(EntityEffect.HURT);
			}
		}
	}
}