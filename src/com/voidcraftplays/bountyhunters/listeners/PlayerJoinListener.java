package com.voidcraftplays.bountyhunters.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.voidcraftplays.bountyhunters.Main;

public class PlayerJoinListener implements Listener {
	
	private Main main;
	
	public PlayerJoinListener(Main main) {
		this.main = main;
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		
		if (!main.getConfig().getStringList("players-joined").contains(player.getName())) {
			
			List<String> playersJoined = main.getConfig().getStringList("players-joined");
			playersJoined.add(player.getName());
			main.getConfig().set("players-joined", playersJoined);
			main.saveConfig();
		}
		
	}
	
}
