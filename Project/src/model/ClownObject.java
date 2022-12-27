package model;

public class ClownObject extends ImageObject {

    private int y;

    private static ClownObject instance = null;

    private ClownObject(int posX, int posY, String path, int id) {
        super(posX, posY, path, id);
    }

    public static synchronized ClownObject getInstance(int posX, int posY, String path, int id) {
        if (instance == null) {
            instance = new ClownObject(posX, posY, path, id);
        }
        return instance;
    }

@Override
public void setY(int y) {
       this.y=y;
    }

}

