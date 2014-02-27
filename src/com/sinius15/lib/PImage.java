package com.sinius15.lib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
 
public class PImage {
     
    private Color color;
    private int widht, height;
    private ArrayList<BufferedImage> images = new ArrayList<>();
     
    public PImage(BufferedImage img, Color seperateColor){
        this.color = seperateColor;
        widht = img.getData().getWidth();                   //widht of the orginal picture
        height = img.getData().getHeight();                 //height of the original picture
        boolean[][] wall = new boolean[widht][height];  //if the pixel is already been prosessed.
         
        Point corner1, corner2;
        Rectangle rec;
         
         
        //first, add all the pink lines to the wall array
        for(int y = 0; y<height;y++)
            for(int x = 0; x< widht; x++)
                if(new Color(img.getRGB(x, y)).equals(color))
                    wall[x][y] = true;
                 
         
         
        for(int y = 0; y<height;y++){
            for(int x = 0; x< widht; x++){
                if(wall[x][y])
                    continue;
 
                //we have entered a new squere!
                corner1 = new Point(x, y);
                int Bx = x, By = y;
                while(true){//get the x of the richtunder corner
                    Bx++;
                    if(Bx >= widht || wall[Bx][By]){
                        Bx--;
                        break;
                    }
                }
                while(true){//get the y of the richtunder conrner
                    By++;
                    if(By >= height || wall[Bx][By]){
                        By--;
                        break;
                    }
                }
                for(int x1 = x; x1<=Bx; x1++){//set everything in the square to wall
                    for(int y1 = y; y1<=By; y1++){
                        wall[x1][y1] = true;
                    }
                }
                //add image
                corner2 = new Point(Bx, By);
                rec = new Rectangle(corner1, new Dimension((corner2.x-corner1.x), (corner2.y-corner1.y)));
                BufferedImage temp = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
                temp.getGraphics().drawImage(img, 0, 0, rec.width, rec.height, corner1.x, corner1.y, corner2.x, corner2.y, null);
                images.add(temp);
                 
            }
        }
    }
     
    public BufferedImage getImageById(int id){
        return images.get(id-1);
    }
     
}
