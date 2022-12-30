package view;

import controller.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import model.*;

public class Game implements World {

    private final List<GameObject> constant = new LinkedList<>();
    private final List<GameObject> moving = new LinkedList<>();
    private final List<GameObject> control = new LinkedList<>();
    private final ShapesFactory factory;
    private final StackController stackControl;
    private final GameController gameControl;
    private final Difficulty world;
    private AudioController audio;

    public Game(GameController gamecontrol, Difficulty world, AudioController audio) {
        this.gameControl = gamecontrol;
        this.world = world;
        this.audio = audio;
        factory = new ShapesFactory(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight());
// control objects 
        control.add(ClownObject.getInstance(gamecontrol.getScreenWidth() / 3, (int) (gamecontrol.getScreenHeight() * 0.65), "./images/clown.png", 10));
        stackControl = StackController.getInstance(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), control.get(0), world.getMovingNum() - world.getBombsNum() + 1, audio);
// moving objects 
        Random r = new Random();
        for (int i = 0; i < world.getMovingNum(); i++) {
            moving.add((GameObject) factory.getShape(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), gamecontrol.getShapes()[r.nextInt(gamecontrol.getShapes().length)]));
        }
        for (int i = 0; i < world.getBombsNum(); i++) {
            moving.add((GameObject) factory.getShape(gamecontrol.getScreenWidth(), gamecontrol.getScreenHeight(), "Bomb"));
        }
// constants objects 
        constant.add(new ImageObject(0, 0, world.getTheme(), 0));
    }

    @Override
    public boolean refresh() {
        for (GameObject m : moving) {
            stackControl.update(m);
            stackControl.handleIntersection(m);
            // Catching 3 of same color
            if (stackControl.verify()) {
                gameControl.setScore(gameControl.getScore() + 10);
            }
            // update falling object after reaching ground
            gameControl.update(m);
            // Catching bombs
            if (stackControl.handleBomb()) {
                gameControl.setScore(Math.max(0, gameControl.getScore() - 10));	// lose score
                gameControl.setLives(gameControl.getLives().length() - 1);
            }
        }
        if (gameControl.isLost(stackControl)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSpeed() {
        return world.getGameSpeed();
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
