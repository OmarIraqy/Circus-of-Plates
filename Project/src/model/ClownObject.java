/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.image.BufferedImage;



public class ClownObject extends ImageObject {
    private int x, y, id;
    private static final int MAX_MSTATE = 1;
    boolean visible;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

    public ClownObject(int posX, int posY, String path, int id) {
        super(posX, posY, path, id);
    }

    @Override
    public void setY(int y) {
       this.y=getY();
    }

}

