package com.sinius15.lbplugin;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import lightBringer2.Game;
import lightBringer2.entities.Direction;
import lightBringer2.entities.DroppedItem;
import lightBringer2.external.LightbringerMod;
import lightBringer2.items.ItemStack;
import lightBringer2.level.Level;

import com.sinius15.lbplugin.entitys.OneWayFlower;
import com.sinius15.lbplugin.entitys.Pipe;
import com.sinius15.lbplugin.entitys.Tank;
import com.sinius15.lbplugin.items.OneWayFlowerItem;
import com.sinius15.lbplugin.items.PipeItem;
import com.sinius15.lbplugin.items.TankItem;
import com.sinius15.lbplugin.items.Wrench;

public class Plugin implements LightbringerMod {
	
	public static SResourceLoader resources = new SResourceLoader();
	public static Wrench wrenchItem = new Wrench((byte)50);
	public static PipeItem pipeItem = new PipeItem((byte)51);
	public static TankItem tankItem = new TankItem((byte)52);
	public static OneWayFlowerItem oneWayFlowerItem = new OneWayFlowerItem((byte)53);
	
	
	@Override
	public void init(Game game) {
		System.out.println("[pipeMod] initting");
		try {
			resources.loadFiles();
		} catch (IOException | URISyntaxException e) {
			JOptionPane.showMessageDialog(null, "An error accured when loading the mod: pipeMod"+System.lineSeparator()+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		System.out.println("[pipeMod] initting done");
	}

	@Override
	public void initLevel(Level level) {
		
		level.add(new Pipe(level, 17, 18, 20));
		level.add(new Pipe(level, 18, 17, 0));
		level.add(new Pipe(level, 18, 18, 0));
		level.add(new Pipe(level, 19, 18, 0));
		level.add(new Pipe(level, 18, 19, 0));
		
		level.add(new OneWayFlower(level, 2, 22,  Direction.LEFT));
		level.add(new Pipe(level, 3, 22,  0));
		level.add(new Pipe(level, 4, 22,  0));
		level.add(new Pipe(level, 5, 22,  0));
		level.add(new Pipe(level, 6, 22,  0));
		level.add(new Pipe(level, 7, 22,  0));
		level.add(new Pipe(level, 8, 22,  0));
		level.add(new Pipe(level, 9, 22,  0));
		level.add(new Pipe(level, 10, 22, 0));
		level.add(new Pipe(level, 11, 22, 0));
		level.add(new Pipe(level, 12, 22, 0));
		level.add(new Pipe(level, 13, 22, 0));
		level.add(new OneWayFlower(level, 14, 22, Direction.LEFT));
		
		level.add(new Tank(level, 1, 23, 100, true));
		level.add(new Tank(level, 1, 22, 100, true));
		level.add(new Tank(level, 1, 21, 100, true));
		level.add(new Tank(level, 1, 20,  0, true));
		level.add(new Tank(level, 1, 19,  0, true));
		level.add(new Tank(level, 1, 18,  0, true));
		level.add(new Tank(level, 1, 17,  0, true));
		
		level.add(new Tank(level, 15, 22, 100, true));
		
		level.add(new Tank(level, 5, 19, 100, true));
		level.add(new OneWayFlower(level, 6, 19, Direction.LEFT));
		level.add(new Tank(level, 7, 19, 0, true));
		
		level.add(new DroppedItem(level, 15, 15, new ItemStack(Plugin.wrenchItem, 0, 1)));
	}

	@Override
	public String getName() {
		return "WaterMaddness";
	}

}
