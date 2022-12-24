package model;
public class ShapesFactory {

    Shapes shape;

    public Shapes getShape(String shapeName) {
        if (shapeName == "GreenPlate") {
            return new PlateObject(20, 10, "C:/Users/omari/Project/Project/src/resources/greenplatewithbase.png");
        } else if (shapeName == "RedPlate") {
            return new PlateObject(100, 10, "C:/Users/omari/Project/Project/src/resources/redplatewithbase.png");
        }
        return null;
    }
}
