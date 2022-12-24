package view;

import controller.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import model.*;

public class Game implements World {

    private static int MAX_TIME = 1 * 60 * 10000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private static List<GameObject> constant = new LinkedList<GameObject>();
    private static List<GameObject> moving = new LinkedList<GameObject>();
    private static List<GameObject> control = new LinkedList<GameObject>();
    private ShapesFactory factory = new ShapesFactory();
    //private Stack<GameObject> stack = new Stack<>();
    private StackController myController;
    private GameController gameController;

    public Game(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        String[] shapes = new String[2];
        shapes[0] = "RedPlate";
        shapes[1] = "GreenPlate";
// control objects 
        control.add(new ImageObject(screenWidth / 2, (int) (screenHeight * 0.85), "./images/special.png", 1));
// moving objects 
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            moving.add((GameObject) factory.getShape(screenWidth, screenHeight, shapes[r.nextInt(shapes.length)]));
        }
        for (int i = 0; i < 2; i++) {
            moving.add((GameObject) factory.getShape(screenWidth, screenHeight, "Bomb"));
        }
// constants objects 
        constant.add(new ImageObject(0, 0, "./images/theme3.png", 0));
        myController = new StackController(screenWidth, screenHeight, control.get(0));
        gameController = new GameController(screenWidth, screenHeight);
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject special = control.get(0);

        for (GameObject m : moving) {// moving starts

            myController.update(m);//update falling object positions and handle caught shapes

            if (myController.verify()) {
                score += 10;
            }
            if (intersect(m, special)) {
                myController.addToStack(m);
            }
            if (intersect(m, special)) {
                myController.addToStack(m);
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
