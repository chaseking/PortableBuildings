package com.chasechocolate.portablebuildings.building;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.chasechocolate.portablebuildings.PortableBuildings;
import com.chasechocolate.portablebuildings.schematic.Schematic;
import com.chasechocolate.portablebuildings.utils.Utilities;

public class Building {
	private String name;
	
	private Schematic schematic;
	
	public Building(Schematic schematic){
		this.schematic = schematic;
		this.name = schematic.getName();
	}
	
	public String getName(){
		return this.name;
	}
	
	public Schematic getSchematic(){
		return this.schematic;
	}
	
	public void build(final Location loc){
		final HashMap<Block, Integer> blocks = new HashMap<Block, Integer>();
		List<Block> allBlocks = new ArrayList<Block>();
		
		for(int x = 0; x < schematic.getWidth(); x++){
			for (int y = 0; y < schematic.getHeight(); y++){
				for (int z = 0; z < schematic.getLength(); ++z){
					Location temp = loc.clone().add(x, y, z);
					Block block = temp.getBlock();
					int index = y * schematic.getWidth() * schematic.getLength() + z * schematic.getWidth() + x;
					
					blocks.put(block, index);
					allBlocks.add(block);
				}
			}
		}
		
		final List<Block> orderedBlocks = new ArrayList<Block>();
		
		orderedBlocks.addAll(allBlocks);
		
		Collections.sort(orderedBlocks, new Comparator<Block>(){
			@Override
			public int compare(Block block1, Block block2){
				return Double.compare(block1.getY(), block2.getY());
			}
		});
		
		final int size = orderedBlocks.size();
		final int blocksPerTime = 1;
		final long delay = 1L;
		
		if(size > 0){
			new BukkitRunnable(){
				int index = 0;
				
				@Override
				public void run(){
					for(int i = 0; i < blocksPerTime; i++){
						if(index < size){
							Block block = orderedBlocks.get(index);
							int otherIndex = blocks.get(block);
							int typeId = schematic.getBlocks()[otherIndex];
							byte data = schematic.getData()[otherIndex];
							
							if(!(block.getLocation().equals(loc))){
								Utilities.regenerateBlock(block, Material.getMaterial(typeId), data);
							}
							
							index += 1;
						} else {
							this.cancel();
							return;
						}						
					}
				}
			}.runTaskTimer(PortableBuildings.getInstance(), 40L, delay);
		}
	}
}