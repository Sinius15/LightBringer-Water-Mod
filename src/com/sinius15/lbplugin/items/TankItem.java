package com.sinius15.lbplugin.items;

import java.awt.Image;

import lightBringer2.entities.Entity;
import lightBringer2.entities.Player;
import lightBringer2.entities.State;
import lightBringer2.items.Item;
import lightBringer2.items.ItemStack;

import com.sinius15.lbplugin.Plugin;
import com.sinius15.lbplugin.entitys.Tank;

public class TankItem extends Item{

	public TankItem(byte id) {
		super(id, -1);
	}

	@Override
	public void use(Entity e, ItemStack stack) {
		if(stack.stack<=0 || !e.state.equals(State.inLevel) || !(e instanceof Player))
			return;
		Tank p = new Tank(e.level, e.x, e.y, 0, true);
		e.level.add(p);
		stack.stack--;
	}
	
	@Override
	public Image getImage(ItemStack stack) {
		return Plugin.resources.getImage("itemTank");
	}
	
	@Override
	public int getCooldown() {
		return 15;
	}
	
	@Override
	public int maxStack() {
		return 32;
	}
}

