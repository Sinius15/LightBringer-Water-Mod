package com.sinius15.lbplugin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import lightBringer2.entities.Direction;
import lightBringer2.entities.Entity;
import lightBringer2.entities.Player;
import lightBringer2.level.Level;
import lightBringer2.level.wires.WireMap;

public abstract class WaterHolder extends Entity {
	
	public float filled = 0;
	public int id;
	private boolean firstTick = true;
	private boolean isPickedUp = false;
	int tickCounter = 0;
	
	public WaterHolder(Level l, int x, int y, int id) { 
		super(l); 
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void PostInit(){
		updateImage();
	}
	
	public WaterHolder(){}
	
	public int getID(){
		return id;
	}
	
	public void updateImage(){}
	
	public boolean canConnect(Direction d){ return true; }
	
	public boolean canMove(){ return true; }
	
	public float getFilled(){ return filled; }
	
	public float maxFilled(){ return 0; }
	
	public float getSpace(){ return maxFilled() - getFilled(); }
	
	//flowMax = 0 means it will never flow
	public float flowMaximum(){ return 100; }
	
	public float flowMinimum(){ return 3; }
	
	@Override
	public void tick() {
		tickCounter++;
		if(isPickedUp)
			return;
		if(firstTick){
			PostInit();
			firstTick = !firstTick;
		}
		if(getFilled() > maxFilled())
			throw new IllegalArgumentException("getFilled() can not be bigger than maxFilled()");
		if(flowMaximum() != 0){
			
			for(Point point : WireMap.getSurroundingPoints(getPos())){
				for(Entity e : level.getEntities(point.x, point.y)){
					if(e instanceof WaterHolder){
						WaterHolder watA = (WaterHolder) e;
						WaterHolder watB = this;
						if(watA.canConnect(Direction.calcDirection(getPos(), watA.getPos()))){
							float delta = Math.abs(watA.getFilled() - this.getFilled());
							if(delta > watA.flowMinimum() && delta > watB.flowMinimum()){
								float flow;
								if(delta >= watA.flowMaximum()){
									flow = 0.5f*watA.flowMaximum();
									if(watB.flowMaximum() < watA.flowMaximum())
										flow = 0.5f*watB.flowMaximum();
								}else{
									flow = 0.5f*delta;
								}
								if(watA.getFilled() > watB.getFilled()){
									if(watB.getSpace() < flow)
										flow = watB.getSpace();
									watA.filled -= flow;
									watB.filled += flow;
								}else if(watA.getFilled() < watB.getFilled()){
									if(watA.getSpace() < flow)
										flow = watA.getSpace();
									watA.filled += flow;
									watB.filled -= flow;
								}
								updateImage();
							}
						}
					}
				}
			}
		}
		if(tickCounter>10){
			tickCounter=0;
			updateImage();
		}
	}
	
	@Override
	public void touch(Entity e) {
		if(canMove() && e instanceof Player){
			//TODO: oppakken van blok;
		}
	}
	
	@Override
	public Image getBattleImage() {
		return null; //never used;   water holders will not be in a battle!
	}
	
	@Override
	public Dimension getBattleSize() {
		return null; //never used;   water holders will not be in a battle!
	}
	
	@Override
	public void touchBattle(Entity e) {
		//never used;   water holders will not be in a battle!
	}

	public Color getWaterColor() {
		float persentageFiled = getFilled()/maxFilled();
		int rg = (int)(persentageFiled*255);
		if(rg < 0)
			rg = 0;
		return new Color(255-rg, 255-rg, 255);
	}

	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean isPickedUp) {
		this.isPickedUp = isPickedUp;
	}
	
	public void updateNeighbours(){
		for(Point point : WireMap.getSurroundingPoints(getPos())){
			for(Entity e : level.getEntities(point.x, point.y)){
				if(e instanceof WaterHolder){
					((WaterHolder) e).updateImage();
				}
			}
		}
	}
}