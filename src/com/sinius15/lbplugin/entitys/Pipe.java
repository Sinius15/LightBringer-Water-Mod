package com.sinius15.lbplugin.entitys;

import java.awt.Image;

import lightBringer2.entities.Entity;
import lightBringer2.level.Level;

import com.sinius15.lbplugin.ImageConstructor;
import com.sinius15.lbplugin.WaterHolder;

public class Pipe extends WaterHolder{

	Image image;
	
	public Pipe(Level l, int x, int y, float fill){
		super(l, x, y, 1);
		filled = fill;
		updateImage();
	}
	
	public Pipe(){
		this.id = 1;
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void updateImage() {
		boolean up = false, down = false, left = false, right = false;
		
		for(Entity e : level.getEntities(x+1, y))
			if(e instanceof WaterHolder){
				right = true;
				break;
			}
		for(Entity e : level.getEntities(x, y+1))
			if(e instanceof WaterHolder){
				down = true;
				break;
			}
		for(Entity e : level.getEntities(x-1, y))
			if(e instanceof WaterHolder){
				left = true;
				break;
			}
		for(Entity e : level.getEntities(x, y-1))
			if(e instanceof WaterHolder){
				up = true;
				break;
			}
		image = ImageConstructor.getPipeImage(up, down, left, right, getWaterColor());
	}

	@Override
	public Image getImage() {
		if(isPickedUp())
			return null;
		return image;
	}

	@Override
	public float maxFilled() {
		return 20;
	}
	
	@Override
	public float flowMaximum() {
		return 0.1f;
	}
	
	@Override
	public float flowMinimum() {
		return 0.0f;
	}

	@Override
	public int getID() {
		return 1;
	}
	
}
