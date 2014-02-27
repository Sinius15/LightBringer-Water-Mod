package com.sinius15.lbplugin.entitys;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import lightBringer2.entities.Direction;
import lightBringer2.entities.Entity;
import lightBringer2.entities.Player;
import lightBringer2.level.Level;

import com.sinius15.lbplugin.ImageConstructor;
import com.sinius15.lbplugin.WaterHolder;

public class OneWayFlower extends WaterHolder{

	Image image;
	Point in, out;
	Direction dirIn, dirOut;
	Color color = Color.white;
	
	public OneWayFlower(Level l, int x, int y, Direction dir){
		super(l, x, y, 2);
		out = Direction.calcNewPoint(this.getPos(), dir);
		in = Direction.calcNewPoint(this.getPos(), Direction.reverseDirection(dir));
		dirIn = Direction.calcDirection(in, getPos());
		dirOut = Direction.calcDirection(out, getPos());
	}
	
	public OneWayFlower(){
		this.id = 2;
	}
	
	@Override
	public void PostInit() {
		super.PostInit();
	}
	
	@Override
	public void updateImage() {
		super.updateImage();
		
		image = ImageConstructor.getOneWayFlowerImage(dirIn, color);
	}
	
	@Override
	public void tick() {
		super.tick();
		WaterHolder watIn = null, watOut = null;
		for(Entity e : level.getEntities(in.x, in.y))
			if(e instanceof WaterHolder){
				watIn = (WaterHolder) e;
				break;
			}
		for(Entity e : level.getEntities(out.x, out.y))
			if(e instanceof WaterHolder){
				watOut = (WaterHolder) e;
				break;
			}
		if(watIn == null || watOut == null)
			return;
		if(!watIn.canConnect(Direction.calcDirection(in, getPos())))
			return;
		if(!watOut.canConnect(Direction.calcDirection(in, getPos())))
			return;
		if(watOut.getSpace() >= 1 && watIn.getFilled() >= 1){
			float flow = 1;
			if(flow >= watIn.flowMaximum()){
				flow = watIn.flowMaximum();
				if(watOut.flowMaximum() < watIn.flowMaximum())
					flow =watOut.flowMaximum();
			}
			watIn.filled -= flow;
			watOut.filled += flow;
			color = watIn.getWaterColor();
		}
	}
	
	@Override
	public void touch(Entity e) {
		super.touch(e);
		if(e instanceof Player){
			Direction c = dirIn;
			dirIn = dirOut;
			dirOut = c;
			
			Point d = in;
			in = out;
			out = d;
			
			out = Direction.calcNewPoint(this.getPos(), dirIn);
			in = Direction.calcNewPoint(this.getPos(), Direction.reverseDirection(dirIn));
			dirIn = Direction.calcDirection(in, getPos());
			dirOut = Direction.calcDirection(out, getPos());
			
			updateImage();
		}
	}
	
	@Override
	public Image getImage() {
		if(isPickedUp())
			return null;
		return image;
	}

	@Override
	public float getFilled() {
		return 0;
	}
	
	@Override
	public boolean canConnect(Direction d) {
		return (dirIn == d || dirOut == d);
	}
	
	@Override
	public float maxFilled() {
		return 0;
	}
	
	@Override
	public float flowMaximum() {
		return 0;
	}
	
	@Override
	public float flowMinimum() {
		return 0;
	}
	
}
