package com.voidcraftplays.bountyhunters.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.voidcraftplays.bountyhunters.Main;

public class BountyCommand implements CommandExecutor {
	
	private Main main;
	
	public BountyCommand(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if (player.hasPermission("bountyhunters.player") || player.hasPermission("bountyhunters.admin")) {
			
				if (args.length == 0) {
					
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7Commands --"));
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty help &7| Prints this message"));
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty place &7| Places a bounty on someone's head"));
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty join &7| Join the hunters team"));
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty quit &7| Stop being a hunter"));
					player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty jobs &7| Lists the hunters jobs"));
					
					if (player.hasPermission("bountyhunters.admin")) {
						
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty coins &7| Adds or removes coins from a player"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty hunter &7| Forces a player to join or quit the hunters"));
						
					}
					
				} else if (args.length == 1) {
					
					switch (args[0]) {
					case "help":
						
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7Commands --"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty help &7| Prints this message"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty place &7| Places a bounty on someone's head"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty join &7| Join the hunters team"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty quit &7| Stop being a hunter"));
						player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty jobs &7| Lists the hunters jobs"));
						
						if (player.hasPermission("bountyhunters.admin")) {
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty coins &7| Adds or removes coins from a player"));
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&e- /bounty hunter &7| Forces a player to join or quit the hunters"));
							
						}
						
						break;
					case "place":
						
						if (args.length == 2) {
							
							int reward = Integer.valueOf(args[1]);
							Player pl = Bukkit.getPlayer(args[2]);
							String name = pl.getName();
							
							if (main.getConfig().getBoolean("jobs." + name + ".completed") == true || !(main.getConfig().contains("jobs." + name))) {
								
								if (Main.getEconomy().getBalance(name) >= reward)
								
									main.placeBounty.placeBounty(player, pl, reward, name);
									Main.getEconomy().bankWithdraw(name, reward);
								
									player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7Your bounty has been placed. A bounty hunter will soon take your job!"));
									main.saveConfig();
									
							} else {
								
								player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7That player already has a bounty on them!"));
								
							}
							
						} else {
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7Invalid usage. &e/bounty place ($ Reward) (Player to be killed)"));
							
						}
						
						break;
					case "join":
						
						if (!(main.getConfig().contains("hunters." + player.getName())) || main.getConfig().getBoolean("hunters." + player.getName() + ".hunter") == false) {
							
							main.getConfig().set("hunters." + player.getName() + ".jobs-completed", 0);
							main.getConfig().set("hunters." + player.getName() + ".bounty-coins", 0);
							main.getConfig().set("hunters." + player.getName() + ".hunter", true);
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7You are now a Bounty Hunter!"));
							main.saveConfig();
							
						} else {
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7You are already a Bounty Hunter!"));
							
						}
						
						break;
					case "quit":
						
						if (main.getConfig().contains("hunters." + player.getName())) {
							
							main.getConfig().set("hunters." + player.getName() + ".hunter", false);
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7You are no longer a Bounty Hunter!"));
							main.saveConfig();
							
						} else {
							
							player.sendMessage(main.prefix + ChatColor.translateAlternateColorCodes('&', "&7You are not currently a Bounty Hunter! Use &e/bounty join"));
							
						}
						
						break;
					case "jobs":
						
						main.jobsUI.jobsUI(player);
						
						break;
					case "coins":
						
						
						
						break;
					case "hunter":
						
						break;
					}
					
				}
			
			}
			
		}
		
		return false;
	}
	
}
