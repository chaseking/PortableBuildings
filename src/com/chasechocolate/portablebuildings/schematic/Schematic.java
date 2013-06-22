package com.chasechocolate.portablebuildings.schematic;


public class Schematic {
	private short width;
	private short height;
	private short length;
	
	private String name;
	private String materials;
	
	private byte[] blocks;
	private byte[] data;
	
	public Schematic(String name, short width, short height, short length, String materials, byte[] blocks, byte[] data){
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.length = length;
		this.materials = materials;
		this.blocks = blocks;
		this.data = data;
	}
	
	public String getName(){
		return this.name;
	}
	
	public short getWidth(){
		return this.width;
	}
	
	public void setWidth(short width){
		this.width = width;
	}
	
	public short getHeight(){
		return this.height;
	}
	
	public void setHeight(short height){
		this.height = height;
	}
	
	public short getLength(){
		return this.length;
	}
	
	public void setLength(short length){
		this.length = length;
	}
	
	public String getMaterials(){
		return this.materials;
	}
	
	public void setMaterials(String materials){
		this.materials = materials;
	}
	
	public byte[] getBlocks(){
		return this.blocks;
	}
	
	public void setBlocks(byte[] blocks){
		this.blocks = blocks;
	}
	
	public byte[] getData(){
		return this.data;
	}
	
	public void setData(byte[] data){
		this.data = data;
	}
}