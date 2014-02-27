package com.sinius15.lbplugin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import lightBringer2.util.ImageUtilities;

import com.sinius15.lib.PImage;

public class SResourceLoader {

	private PImage gridImage;
	private HashMap<String, Integer> hash = new HashMap<>();
	
	public BufferedImage pipePiece0, pipePiece90, pipePiece180, pipePiece270;
	
	public void loadFiles() throws IOException, URISyntaxException{
		
		hash.put("pipeBase", 1);
		hash.put("pipePiece", 2);
		hash.put("tank", 3);
		hash.put("oneWayFlower", 4);
		
		hash.put("itemWrench", 11);
		hash.put("itemTank", 12);
		hash.put("itemPipe", 12);
		hash.put("itemOneWayFlower", 12);
		
		File pluginFoler = new File(Plugin.class.getResource("res/").toURI());
		
		gridImage = new PImage(ImageIO.read(new File(pluginFoler,"grid.png")), Color.magenta);
		
		pipePiece0 = gridImage.getImageById(2);
		pipePiece90 = ImageUtilities.rotate(gridImage.getImageById(2), 90);
		pipePiece180 = ImageUtilities.rotate(gridImage.getImageById(2), 180);
		pipePiece270 = ImageUtilities.rotate(gridImage.getImageById(2), 270);
	}
	
	public BufferedImage getImage(String key){
		return gridImage.getImageById(hash.get(key));
	}
	
}
