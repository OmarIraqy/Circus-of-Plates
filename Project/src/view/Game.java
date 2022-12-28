package view;

import controller.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import model.*;

public class Game implements World {

    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private ShapesFactory factory;
    private StackController stackControl;
    private GameController gameControl;
    private final int speed;

    public Game(GameController gamecontrol, int speed, String theme, String diff) {
        this.gameControl = gamecontrol;
        this.speed = speed;
        String[] shapes = new String[6];
        shapes[0] = "RedPlate";
        shapes[0] = "OrangePlate";
        shapes[1] = "GreenPlate";
        shapes[2] = "BluePlate";
        shapes[3] = "PinkPlate";
        shapes[4] = "YellowPlate";
        shapes[5] = "OrangePlate";
        shapes[5] = "RedPlate";
        factory = new ShapesFactory(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight());

// control objects 
        control.add(ClownObject.getInstance(gamecontrol.getScreenWidth() / 3, (int) (gamecontrol.getScreenHeight() * 0.65), "./images/clown.png", 10));
// moving objects 
        Random r = new Random();
//        for (int i = 0; i < gameControl.getMovingNumber(); i++) {
//            moving.add((GameObject) factory.getShape(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), shapes[r.nextInt(shapes.length)]));
//        }
//        for (int i = 0; i < gameControl.getbombsNumber(); i++) {
//            moving.add((GameObject) factory.getShape(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), "Bomb"));
//        }
//// constants objects 
//        constant.add(new ImageObject(0, 0, theme, 0));
//        stackControl = StackController.getInstance(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), control.get(0),
//                difficulity.getMovingNumber() - difficulity.getbombsNumber() + 1);
    }

    private boolean intersectLeft(GameObject o1, GameObject o2) {
        return (o1.getHeight() + o1.getY() == o2.getY() && o1.getX() + o1.getWidth() / 2 <= o2.getX() + (o2.getWidth() * 38) / 153 && o1.getX() + o1.getWidth() / 2 >= o2.getX());
    }

    private boolean intersectRight(GameObject o1, GameObject o2) {
        return (o1.getHeight() + o1.getY() == o2.getY() && o1.getX() + o1.getWidth() / 2 <= o2.getX() + o2.getWidth() && o1.getX() + o1.getWidth() / 2 >= o2.getX() + (o2.getWidth() * 115) / 153);
    }

    @Override
    public boolean refresh() {
        GameObject clown = control.get(0);
        for (GameObject m : moving) {
            stackControl.update(m);
            if (stackControl.isLeftEmpty()) {
                if (intersectLeft(m, clown)) {
                    stackControl.addToStack(m, 1);
                }
            }
            if (stackControl.isRightEmpty()) {
                if (intersectRight(m, clown)) {
                    stackControl.addToStack(m, 2);
                }
            }
            if (stackControl.verify()) {
                gameControl.setScore(gameControl.getScore() + 10);
            }
            // update falling object after reaching ground
            gameControl.update(m);
            // handle catching bombs
            if (stackControl.handleBomb()) {
                gameControl.setScore(Math.max(0, gameControl.getScore() - 10));	// lose score
                gameControl.setLives(gameControl.getLives().length() - 1);
            }
        }
        return !(gameControl.isLost(stackControl));
    }

    @Override
    public int getSpeed() {
        return speed;
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
        return gameControl.getScreenWidth();
    }

    @Override
    public int getHeight() {
        return gameControl.getScreenHeight();
    }

    @Override
    public String getStatus() {
        return ("Score= " + gameControl.getScore() + " Lives: ️" + gameControl.getLives());	// update status
    }
}
