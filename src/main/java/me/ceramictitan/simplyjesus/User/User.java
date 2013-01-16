package me.ceramictitan.simplyjesus.User;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class User {

	public static Set<String> godlist = new HashSet<String>();

	public static void enableGodMode(Player p) {
		godlist.add(p.getName());
	}

	public static void disableGodMode(Player p) {
		godlist.remove(p.getName());
	}

}
