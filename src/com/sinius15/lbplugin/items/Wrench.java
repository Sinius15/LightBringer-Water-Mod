package com.sinius15.lbplugin.items;

import java.awt.Image;

import com.sinius15.lbplugin.Plugin;
import com.sinius15.lbplugin.WaterHolder;
import com.sinius15.lbplugin.entitys.OneWayFlower;
import com.sinius15.lbplugin.entitys.Pipe;
import com.sinius15.lbplugin.entitys.Tank;

import lightBringer2.entities.Entity;
import lightBringer2.entities.Player;
import lightBringer2.entities.State;
import lightBringer2.items.Item;
import lightBringer2.items.ItemStack;

public class Wrench extends Item{
	
	public Wrench(byte id) {
		super(id, -1);
	}

	@Override
	public void use(ItemStack stack,Entity e) {
		if(e instanceof Player){
			if(((Player) e ).state == State.inLevel){
				for(Entity ent : e.level.getEntities(e.x, e.y)){
					if(ent instanceof WaterHolder){
						WaterHolder wat = (WaterHolder) ent;
						if(wat.canMove()){
							if(wat.getID() == new Pipe().getID()){
								wat.level.player.pickUp(new ItemStack(Plugin.pipeItem, -1, 1));
								wat.remove();
							}
							if(wat.getID() == new Tank().getID()){
								wat.level.player.pickUp(new ItemStack(Plugin.tankItem, -1, 1));
								wat.remove();
							}
							if(wat.getID() == new OneWayFlower().getID()){
								wat.level.player.pickUp(new ItemStack(Plugin.oneWayFlowerItem, -1, 1));
								wat.remove();
							}
						}
					}
				}	
			}
		}
	}
	
	@Override
	public Image getImage(ItemStack stack) {
		return Plugin.resources.getImage("itemWrench");
	}
	
	@Override
	public int getCooldown() {
		return 15;
	}
	
	@Override
	public int maxStack() {
		return 1;
	}

}