package model;

import controller.ImageController;
import java.util.Random;

public class ShapesFactory {

    Shapes shape;
    ImageObject[] greenPlates = new ImageObject[4];
    ImageObject[] bluePlates = new ImageObject[4];
    ImageObject[] orangePlates = new ImageObject[4];
    ImageObject[] pinkPlates = new ImageObject[4];
    ImageObject[] redPlates = new ImageObject[4];
    ImageObject[] yellowPlates = new ImageObject[4];
    ImageController imageController = new ImageController();

    public ShapesFactory() {
        greenPlates = imageController.getPlates(900, 600, "green", 1);
        bluePlates = imageController.getPlates(900, 600, "blue", 3);
        orangePlates = imageController.getPlates(900, 600, "orange", 4);
        pinkPlates = imageController.getPlates(900, 600, "pink", 5);
        redPlates = imageController.getPlates(900, 600, "red", 6);
        yellowPlates = imageController.getPlates(900, 600, "yellow", 7);
    }

    public Shapes getShape(int width, int height, String shapeName) {
        Random r = new Random();

        if (shapeName == "GreenPlate") {
            return greenPlates[r.nextInt(4)];
        } else if (shapeName == "RedPlate") {
            return redPlates[r.nextInt(4)];
        } else if (shapeName == "BluePlate") {
            return bluePlates[r.nextInt(4)];
        } else if (shapeName == "PinkPlate") {
            return pinkPlates[r.nextInt(4)];
        } else if (shapeName == "OrangePlate") {
            return orangePlates[r.nextInt(4)];
        } else if (shapeName == "YellowPlate") {
            return yellowPlates[r.nextInt(4)];
        } else if (shapeName == "Bomb") {
            return new ImageObject((int) (Math.random() * (width / 2)), -1 * (int) (Math.random() * height), "./images/Bomb.png", 100);
        }
        return null;
    }
}
