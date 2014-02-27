package com.sinius15.lbplugin.entitys;

import java.awt.Image;

import lightBringer2.entities.Direction;
import lightBringer2.level.Level;

import com.sinius15.lbplugin.ImageConstructor;
import com.sinius15.lbplugin.WaterHolder;

public class Tank extends WaterHolder{

	Image image;
	boolean canMove;
	
	public Tank(Level l, int x, int y, float fill, boolean canMove){
		super(l, x, y, 10);
		filled = fill;
		this.canMove = canMove;
		updateImage();
	}
	
	public Tank(){
		this.id = 10;
	}
	
	@Override
	public void updateImage() {
		image = ImageConstructor.getTankImage(filled, maxFilled());
	}

	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public Image getImage() {
		if(isPickedUp())
			return null;
		return image;
	}
	
	@Override
	public boolean canMove() {
		return canMove;
	}
	
	@Override
	public float maxFilled() {
		return 100;
	}
	
	@Override
	public float flowMaximum() {
		return 1f;
	}
	
	@Override
	public float flowMinimum() {
		return 0.0f;
	}
	
	@Override
	public boolean canConnect(Direction d) {
		return true;
	}

}
