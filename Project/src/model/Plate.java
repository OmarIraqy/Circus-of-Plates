package model;

import java.awt.image.BufferedImage;


public class Plate extends Shape {

    private static final int MAX_MSTATE = 100;
    BufferedImage[] spriteImage=new BufferedImage[MAX_MSTATE];
    
    
    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImage;
    }
    
    
}
