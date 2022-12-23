package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlateObject extends Shapes {

    private int x,y;
    private static final int MAX_MSTATE = 100;
    private String picpath;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

    public PlateObject(int posX, int posY, String path){
		this.x = posX;
		this.y = posY;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    
    
    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }
}
