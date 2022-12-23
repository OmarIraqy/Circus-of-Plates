package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlateObject extends Shapes {

    private int x,y;
    private static final int MAX_MSTATE = 1;
    private String picpath;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

    public PlateObject(int posX, int posY, String path){
		this.x = posX;
		this.y = posY;
		this.visible = true;
		try {
			spriteImages[0] = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }
}