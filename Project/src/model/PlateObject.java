package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlateObject extends Shapes {

    private static final int MAX_MSTATE = 100;
    private String picpath;
    private BufferedImage[] spriteImage;

    public PlateObject() {
        this.spriteImage = new BufferedImage[MAX_MSTATE];
        /*
        Set picpath to choose random plate pic from array of path
         */
        if (picpath != null) {
            try {
                spriteImage[0] = ImageIO.read(new File(picpath));

            } catch (IOException ex) {
                System.out.println("Can't load Plate pics");
            }
        }
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImage;
    }
}
