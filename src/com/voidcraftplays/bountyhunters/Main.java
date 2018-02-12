package com.voidcraftplays.bountyhunters;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.voidcraftplays.bountyhunters.commands.BountyCommand;
import com.voidcraftplays.bountyhunters.util.JobsUI;
import com.voidcraftplays.bountyhunters.util.PlaceBounty;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public PlaceBounty placeBounty;
	public JobsUI jobsUI;
    public static Economy economy = null;
	
	public String prefix = ChatColor.translateAlternateColorCodes('&', "&6&lBOUNTY&7| ");
	
	public void onEnable() {
		
		System.out.println("Bounty Hunters has been enabled.");
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getCommand("bounty").setExecutor(new BountyCommand(this));
		
		placeBounty = new PlaceBounty(this);
		jobsUI = new JobsUI(this);
		
		setupEconomy();
		
	}
	
	private boolean setupEconomy() {
		
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
        	
            economy = economyProvider.getProvider();
            
        }

        return (economy != null);
    }
	
	public static Economy getEconomy() {
		
        return economy;
        
    }
	
	/*
	 * 
	 * Permissions:
	 * - bountyhunters.player
	 * - bountyhunters.admin
	 * 
	 */
	
}
