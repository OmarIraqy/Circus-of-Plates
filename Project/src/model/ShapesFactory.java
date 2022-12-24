package model;

public class ShapesFactory {

    Shapes shape;

    public Shapes getShape(int width,int height, String shapeName) {
        if (shapeName == "GreenPlate") {
            return new PlateObject((int) (Math.random() * (width/2)), -1 * (int) (Math.random() * height), "./images/greenplatewithbase.png",1);
        } else if (shapeName == "RedPlate") {
            return new PlateObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/redplatewithbase.png",2);
        }
        return null;
    }
}
