package model;
public class ShapesFactory {

    Shapes shape;

    public Shapes getShape(int width,int height, String shapeName) {
        if (shapeName == "GreenPlate") {
<<<<<<< HEAD
            return new PlateObject(20, 10, "C:/Users/omari/Project/Project/src/resources/greenplatewithbase.png");
        } else if (shapeName == "RedPlate") {
            return new PlateObject(100, 10, "C:/Users/omari/Project/Project/src/resources/redplatewithbase.png");
=======
            return new PlateObject((int) (Math.random() * (width/2)), -1 * (int) (Math.random() * height), "./images/greenplatewithbase.png",1);
        } else if (shapeName == "RedPlate") {
            return new PlateObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/redplatewithbase.png",2);
>>>>>>> Asser
        }
        return null;
    }
}
