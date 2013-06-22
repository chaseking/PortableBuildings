package com.chasechocolate.portablebuildings;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.chasechocolate.portablebuildings.building.BuildingUtils;
import com.chasechocolate.portablebuildings.listeners.PlayerInteractListener;
import com.chasechocolate.portablebuildings.schematic.SchematicUtils;
import com.chasechocolate.portablebuildings.utils.ItemUtils;

public class PortableBuildings extends JavaPlugin {
	private static PortableBuildings instance;
	
	private File baseSchematicsFile;
	
	public void log(String msg){
		this.getLogger().info(msg);
	}
	
	@Override
	public void onEnable(){
		instance = this;
		
		PluginManager pm = this.getServer().getPluginManager();
		
		pm.registerEvents(new PlayerInteractListener(), this);
		
		ItemUtils.initItems();
		
		ShapedRecipe recipe = new ShapedRecipe(ItemUtils.BUILDING_SELECTOR);
		
		recipe.shape("ccc", "crc", "ccc");
		recipe.setIngredient('r', Material.REDSTONE);
		recipe.setIngredient('c', Material.CHEST);
		
		this.getServer().addRecipe(recipe);
		
		baseSchematicsFile = new File(this.getDataFolder() + File.separator + "schematics");
		
		if(!(baseSchematicsFile.exists())){
			baseSchematicsFile.mkdirs();
		}
		
		SchematicUtils.initFile(baseSchematicsFile);
		SchematicUtils.initSchematics();
		
		BuildingUtils.initBuildings();
		
		log("Loaded " + SchematicUtils.getAllSchematics().size() + " schematic(s)!");
		log("Enabled!");
	}
	
	@Override
	public void onDisable(){
		log("Disabled!");
	}
	
	public static PortableBuildings getInstance(){
		return instance;
	}
}