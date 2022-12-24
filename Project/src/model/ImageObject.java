package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageObject implements Shapes, GameObject {

    private int x, y, id;
    private static final int MAX_MSTATE = 1;
    boolean visible;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

    public ImageObject(int posX, int posY, String path, int id) {
        this.x = posX;
        this.y = posY;
        this.id = id;
        this.visible = true;
        try {
            spriteImages[0] = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return this.spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return this.spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
}

