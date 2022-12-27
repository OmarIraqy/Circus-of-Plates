package view;

import controller.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import model.*;

public class Game implements World {

    private static int MAX_TIME = 1 * 60 * 10000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private ShapesFactory factory; 
    private Stack<GameObject> stackLeft = new Stack<>();
    private Stack<GameObject> stackRight = new Stack<>();
    private StackController myController;
    private GameController gameController;
    public Game(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;

        String[] shapes = new String[6];
        shapes[0] = "RedPlate";

        factory = new ShapesFactory(screenWidth,screenHeight);
        shapes[0] = "OrangePlate";

        shapes[1] = "GreenPlate";
        shapes[2] = "BluePlate";
        shapes[3] = "PinkPlate";
        shapes[4] = "YellowPlate";
        shapes[5] = "OrangePlate";

       // shapes[5] = "RedPlate";
        
// control objects 
        control.add(ClownObject.getInstance(screenWidth / 3, (int) (screenHeight * 0.65), "./images/clown.png", 10));
// moving objects 
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            
        }
        for (int i = 0; i < 25; i++) {
            moving.add((GameObject) factory.getShape(screenWidth, screenHeight, shapes[r.nextInt(shapes.length)]));
        }
        for (int i = 0; i < 2; i++) {
            moving.add((GameObject) factory.getShape(screenWidth, screenHeight, "Bomb"));
        }
// constants objects 
        constant.add(new ImageObject(0, 0, "./images/theme3.png", 0));
        myController = StackController.getInstance(screenWidth, screenHeight, control.get(0));
        gameController = GameController.getInstance(screenWidth, screenHeight);
    }

    private boolean intersectLeft(GameObject o1, GameObject o2) {
        return (o1.getHeight() + o1.getY() == o2.getY() && o1.getX() + o1.getWidth() / 2 <= o2.getX() + (o2.getWidth() * 38) / 153 && o1.getX() + o1.getWidth() / 2 >= o2.getX());
        //(Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + 2)) <= 6) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() )) <= o1.getHeight());
    }

    private boolean intersectRight(GameObject o1, GameObject o2) {
        return (o1.getHeight() + o1.getY() == o2.getY() && o1.getX() + o1.getWidth() / 2 <= o2.getX() + o2.getWidth() && o1.getX() + o1.getWidth() / 2 >= o2.getX() + (o2.getWidth() * 115) / 153);
    }



    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject clown = control.get(0);
        // moving starts
        for (GameObject m : moving) {        
            myController.update(m);//update falling object positions and handle caught shapes
            if (myController.isLeftEmpty()) {
                if (intersectLeft(m, clown)) {
                    myController.addToStack(m, 1);
                }
            }
            if (myController.isRightEmpty()) {
                if (intersectRight(m, clown)) {
                    myController.addToStack(m, 2);
                }
            }
            if (myController.verify()) {
                score += 10;
            }
            gameController.update(m);// update falling object after reaching ground
            if (myController.handleBomb()) {// handle catching bombs
                score = Math.max(0, score - 10);	// lose score
            }
        }
        return !timeout;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 10;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }
}
