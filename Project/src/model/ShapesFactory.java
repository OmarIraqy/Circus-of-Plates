package model;

public abstract class ShapesFactory {

    Shapes shape;

    public Shapes getShape(String shapeName) {
        if (shapeName == "Plate") {
            return new PlateObject();
            /*
            elseif conditions to be constructed once 
            anothe object is created
             */

        }
        return null;
    }
}
