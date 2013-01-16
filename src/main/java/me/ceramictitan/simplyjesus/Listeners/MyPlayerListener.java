package me.ceramictitan.simplyjesus.Listeners;

import me.ceramictitan.simplyjesus.User.User;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class MyPlayerListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = ((Player) event.getEntity());
			;
			if (User.godlist.contains(player.getName())) {
				event.setCancelled(true);
				player.playEffect(EntityEffect.HURT);
			}
		}
	}
}