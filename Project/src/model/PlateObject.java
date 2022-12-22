package model;

import java.awt.image.BufferedImage;


public class PlateObject implements Shapes {

    private static final int MAX_MSTATE = 100;
    BufferedImage[] spriteImage=new BufferedImage[MAX_MSTATE];
    
    
    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImage;
    }

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
