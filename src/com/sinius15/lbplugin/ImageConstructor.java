package com.sinius15.lbplugin;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import lightBringer2.entities.Direction;
import lightBringer2.util.ImageUtilities;

public class ImageConstructor {

	public static Image getPipeImage(boolean up, boolean down, boolean left, boolean right, Color colour){
		BufferedImage base = Plugin.resources.getImage("pipeBase");
		BufferedImage out  = new BufferedImage(base.getWidth(),base.getHeight(),BufferedImage.TYPE_INT_ARGB);
		out.getGraphics().drawImage(base, 0, 0, null);
		
		if(up)
			out.getGraphics().drawImage(Plugin.resources.pipePiece0, 0, 0, null);
		if(down)
			out.getGraphics().drawImage(Plugin.resources.pipePiece180, 0, 0, null);
		if(right)
			out.getGraphics().drawImage(Plugin.resources.pipePiece90, 0, 0, null);
		if(left)
			out.getGraphics().drawImage(Plugin.resources.pipePiece270, 0, 0, null);
		
		return ImageUtilities.changeColorImage(out, Color.red, colour);
	}
	
	public static Image getTankImage(float filled, float maxFilled){
		float persentageFiled = filled/maxFilled;
		int rg = (int)(persentageFiled*255);
		if(rg < 0)
			rg = 0;
		return ImageUtilities.changeColorImage(Plugin.resources.getImage("tank"), Color.red, new Color(255-rg, 255-rg, 255));
	}
	
	public static Image getOneWayFlowerImage(Direction dir, Color clr){
		BufferedImage out = null;
		if(dir == Direction.RIGHT)
			out =  Plugin.resources.getImage("oneWayFlower");
		if(dir == Direction.DOWN)
			out =  ImageUtilities.rotate(Plugin.resources.getImage("oneWayFlower"), 90);
		if(dir == Direction.LEFT)
			out =  ImageUtilities.rotate(Plugin.resources.getImage("oneWayFlower"), 180);
		if(dir == Direction.UP)
			out =  ImageUtilities.rotate(Plugin.resources.getImage("oneWayFlower"), 270);
		return ImageUtilities.changeColorImage(out, Color.red, clr);
	}
	
}
