package com.voidcraftplays.bountyhunters.util;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.voidcraftplays.bountyhunters.Main;

public class PlaceBounty {
	
	private Main main;
	
	public PlaceBounty(Main main) {
		this.main = main;
	}
	
	public void placeBounty(Player player, Player pl, int reward, String name) {
		
		pl.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7A bounty has been placed on your head for $" + reward));
		player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7A bounty has been placed on " + name + "'s head for $" + reward));
		
		main.getConfig().set("jobs." + name + ".prize", reward);
		main.getConfig().set("jobs." + name + ".completed", false);
		main.getConfig().set("jobs." + name + ".attacker", "");
		
		List<String> jobs = main.getConfig().getStringList("jobsList");
		jobs.add(name);
		main.saveConfig();
		
	}
	
}
