package com.chasechocolate.portablebuildings.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
	public static ItemStack BUILDING_SELECTOR;
	public static ItemStack BUILDING_PLACER;
	
	public static void initItems(){
		BUILDING_SELECTOR = setNameAndLore(new ItemStack(Material.NETHER_STAR), ChatColor.GREEN + "" + ChatColor.BOLD + "Building Selector", Arrays.asList(ChatColor.DARK_PURPLE + "Right click to select a", ChatColor.DARK_PURPLE + "building to create!"));
		BUILDING_PLACER = setNameAndLore(new ItemStack(Material.ENDER_CHEST), ChatColor.GREEN + "" + ChatColor.BOLD + "Build Building", Arrays.asList(ChatColor.DARK_PURPLE + "Place this block to build", ChatColor.DARK_PURPLE + "the selected building!"));
	}
	
	public static ItemStack setNameAndLore(ItemStack item, String name, List<String> lore){
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		
		if(lore != null){
			meta.setLore(lore);
		}
		
		item.setItemMeta(meta);
		
		return item;
	}
}