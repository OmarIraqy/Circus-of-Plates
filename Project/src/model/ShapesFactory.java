package model;

public class ShapesFactory {

    public Shapes getShape(int width,int height, String shapeName) {
        if ("GreenPlate".equals(shapeName)) {
            return new ImageObject((int) (Math.random() * (width/2)), -1 * (int) (Math.random() * height), "./images/greenPlate.png",1);
        } else if ("RedPlate".equals(shapeName)) {
            return new ImageObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/redPlate.png",2);
        }else if("Bomb".equals(shapeName)){
            return new ImageObject((int) (Math.random() *(width/2)), -1 * (int) (Math.random() * height), "./images/Bomb.png",3);
        }
        return null;
    }
}
