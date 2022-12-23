package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;

public abstract class Shapes implements GameObject {

    private int x, y, width, height;
    boolean visible;

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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public abstract BufferedImage[] getSpriteImages();
}
