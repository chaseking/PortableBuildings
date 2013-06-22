package com.chasechocolate.portablebuildings.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.chasechocolate.portablebuildings.building.Building;
import com.chasechocolate.portablebuildings.building.BuildingUtils;
import com.chasechocolate.portablebuildings.utils.ItemUtils;
import com.chasechocolate.portablebuildings.utils.Utilities;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack hand = player.getItemInHand();
		
		if(hand.equals(ItemUtils.BUILDING_SELECTOR)){
			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
				BuildingUtils.openBuildSelectorMenu(player);
			}
		}
		
		if(hand.equals(ItemUtils.BUILDING_PLACER)){
			if(action == Action.RIGHT_CLICK_BLOCK){
				Block block = event.getClickedBlock();
				Location loc = block.getLocation();
				Building building = BuildingUtils.getPlayerBuilding(player);
				
				if(building != null){
					player.sendMessage(ChatColor.GREEN + "Your building is being built, left click the ender chest to remove it!");
					building.build(loc.add(0.0D, 1.0D, 0.0D));
				} else {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + "Select a building first!");
				}
			}
		}
		
		if(action == Action.LEFT_CLICK_BLOCK){
			Block block = event.getClickedBlock();
			
			if(block.getType() == Material.ENDER_CHEST){
				if(BuildingUtils.isBuildingBlock(block.getLocation())){
					event.setCancelled(true);
					player.sendMessage(ChatColor.GREEN + "Your building is being removed...");
					Utilities.regenerateBlock(block, Material.AIR, (byte) 0);
					BuildingUtils.removeBuilding(block.getLocation());
					
					if(!(player.getInventory().contains(ItemUtils.BUILDING_PLACER))){
						player.getInventory().addItem(ItemUtils.BUILDING_PLACER);
					}
				}
			}
		}
		
		if(action == Action.RIGHT_CLICK_BLOCK){
			Block block = event.getClickedBlock();
			
			if(block.getType() == Material.ENDER_CHEST){
				if(BuildingUtils.isBuildingBlock(block.getLocation())){
					event.setCancelled(true);
				}
			}
		}
	}
}