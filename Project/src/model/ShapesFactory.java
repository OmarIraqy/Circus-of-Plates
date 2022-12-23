package model;

public class ShapesFactory {

    Shapes shape;

    public Shapes getShape(String shapeName) {
        if (shapeName == "GreenPlate") {
            return new PlateObject(20, 10, "C:/Users/Blu-Ray/OneDrive/Desktop/Plates/test.png");
        } else if (shapeName == "RedPlate") {
            return new PlateObject(100, 10, "C:/Users/Blu-Ray/OneDrive/Desktop/Plates/test2.png");
        }
        return null;
    }
}
