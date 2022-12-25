package model;

public class ShapesFactory {

    Shapes shape;

    public Shapes getShape(int width,int height, String shapeName) {
        if (shapeName == "GreenPlate") {
            return new ImageObject((int) (Math.random() * (width/2)), -1 * (int) (Math.random() * height), "./images/greenPlate.png",1);
        } else if (shapeName == "RedPlate") {
            return new ImageObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/redPlate.png",2);
        }else if(shapeName=="Bomb")
        {
            return new ImageObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/Bomb.png",3);
        }
        return null;
    }
}
