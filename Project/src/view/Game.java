package view;

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
    private ShapesFactory factory = new ShapesFactory();
    private Stack<GameObject> stack = new Stack<>();

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
        constant.add(new ImageObject(0,0,"./images/theme3.png" , 0));
        //for(int i=0; i<5; i++)
        //constant.add(new ImageObject((int)(screenWidth*0.9*Math.random()), (int)(screenHeight*0.9*Math.random()), "/astronaut.png"));
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject special = control.get(0);
        // moving starts
        for (GameObject m : moving) {
            if (stack.search(m) == -1) {
                m.setY((m.getY() + 1));
            }
            if (!stack.isEmpty()) {
                stack.get(0).setX(special.getX());
                stack.get(0).setY(special.getY() - 2);
                for (int i = 1; i < stack.size(); i++) {
                    stack.get(i).setX(stack.get(i - 1).getX());
                    stack.get(i).setY(stack.get(i - 1).getY() - 10);
                }
            }
            if (stack.size() >= 3) {
                Shapes object1 = (Shapes) stack.get(stack.size() - 1);
                Shapes object2 = (Shapes) stack.get(stack.size() - 2);
                Shapes object3 = (Shapes) stack.get(stack.size() - 3);
                if (object1.getId() == object2.getId() && object1.getId() == object3.getId()) {
                    score += 10;
                    for (int i = 0; i < 3; i++) {
                        stack.peek().setY(-1 * (int) (Math.random() * getHeight()));
                        stack.peek().setX((int) (Math.random() * getWidth()));
                        stack.pop();
                    }
                }
            }
            if (intersect(m, special)) {
                stack.push(m);
            }
            
            if (m.getY() > getHeight()) {
                /* Falling object has reached the ground reuse it */
                m.setY(-1 * (int) (Math.random() * getHeight()));
                m.setX((int) (Math.random() * getWidth()));
            }  
            for (int i = 0; i < stack.size(); i++) {
                    Shapes object = (Shapes) stack.get(i);
                    if (object.getId() == 3) {
                        System.out.println(stack.size());
                        for (int j = 0 ; !stack.isEmpty() ; j++) {  
                            System.out.println(j+" popped");
                            stack.peek().setY(-1 * (int) (Math.random() * getHeight()));
                            stack.peek().setX((int) (Math.random() * getWidth()));
                            stack.pop();
                        }
                    }
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
