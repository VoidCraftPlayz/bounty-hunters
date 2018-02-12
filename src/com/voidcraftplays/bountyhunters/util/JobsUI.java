package com.voidcraftplays.bountyhunters.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.voidcraftplays.bountyhunters.Main;

public class JobsUI {
	
	private Main main;
	
	public JobsUI(Main main) {
		this.main = main;
	}
	
	public void jobsUI(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 56, ChatColor.translateAlternateColorCodes('&', "&7-- Jobs --"));
		
		ItemStack job = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta jobMeta = job.getItemMeta();
		
		List<String> jobs = main.getConfig().getStringList("jobsList");
		
		for (int i = 0; i > jobs.size(); i++) {
			
			String name = jobs.get(i);
			
			List<String> lore = new ArrayList<>();
			
			lore.add(ChatColor.translateAlternateColorCodes('&', "&7Reward: &a$" + main.getConfig().getInt("jobs." + name + ".prize")));
			
			jobMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a" + name));
			jobMeta.setLore(lore);
			job.setItemMeta(jobMeta);
			
			inv.setItem(i, job);
			
		}
		
		player.openInventory(inv);
		
	}
	
}
