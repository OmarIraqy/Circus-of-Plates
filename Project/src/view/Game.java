/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import model.PlateObject;
import model.ShapesFactory;

/**
 *
 * @author omari
 */
public class Game implements World {

    private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    ShapesFactory factory = new ShapesFactory();
    Random r = new Random();

    public Game(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        String[] shapes = new String[2];
        shapes[0] = "RedPlate";
        shapes[1] = "GreenPlate";
// control objects 
        //control.add(new PlateObject((int) (screenWidth * Math.random()), (int) (screenHeight * Math.random()), "C:/Users/Blu-Ray/OneDrive/Desktop/Plates/test.png"));
// moving objects 

        for (int i = 0; i < 4; i++) {
            moving.add((GameObject) factory.getShape(shapes[r.nextInt(shapes.length)]));
        }
// constants objects 
        //for(int i=0; i<5; i++)
        //constant.add(new PlateObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), "/astronaut.png"));
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        // GameObject PlateObject = control.get(0);
        // moving starts

        for (GameObject m : moving) {
            m.setY((m.getY() + 1));
            //if (m.getY() == getHeight()) {
            // reuse the star in another position
            // m.setY(-1 * (int) (Math.random() * getHeight()));
            //m.setX((int) (Math.random() * getWidth()));
        }
//                  m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
//        if (!timeout & intersect(m, null)) {
//                  score = Math.max(0, score - 10);	// lose score
//            }
//        }
//         collecting astronauts
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
