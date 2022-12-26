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
    private Stack<GameObject> stackLeft = new Stack<>();
    private Stack<GameObject> stackRight = new Stack<>();

    public Game(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        String[] shapes = new String[2];
        shapes[0] = "RedPlate";
        shapes[1] = "GreenPlate";
// control objects 
        control.add(new ImageObject(screenWidth / 2, (int) (screenHeight*0.6 ), "./images/clown with 2 sticks.png", 1));
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

    private boolean intersectLeft(GameObject o1, GameObject o2) {
        return (o1.getHeight()+o1.getY()== o2.getY() && o1.getX()+o1.getWidth()/2<=o2.getX()+(o2.getWidth()*38)/153 && o1.getX()+o1.getWidth()/2>=o2.getX());
               //(Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + 2)) <= 6) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() )) <= o1.getHeight());
    }
    private boolean intersectRight(GameObject o1, GameObject o2) {
        return (o1.getHeight()+o1.getY()== o2.getY() && o1.getX()+o1.getWidth()/2<=o2.getX()+o2.getWidth() && o1.getX()+o1.getWidth()/2>=o2.getX()+(o2.getWidth()*115)/153);
    }
    private boolean intersectPlates(GameObject o1, GameObject o2) {
        return (o1.getHeight()+o1.getY()== o2.getY() && o1.getX()+o1.getWidth()/2<=o2.getX()+(o2.getWidth()*3)/4 && o1.getX()+o1.getWidth()/2>=o2.getX()+o2.getWidth()*0.25);
               //(Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + 2)) <= 6) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() )) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject clown = control.get(0);
        // moving starts
        for (GameObject m : moving) {
            if (stackLeft.search(m) == -1) {
                m.setY((m.getY() + 1));
            }
            if (stackLeft.isEmpty()){    
                if (intersectLeft(m, clown)) {
                    stackLeft.push(m);
                }
            }
            if (stackRight.isEmpty()){    
                if (intersectRight(m, clown)) {
                    stackRight.push(m);
                }
            }
            if (!stackLeft.isEmpty()) {
                stackLeft.get(0).setX(clown.getX()-stackLeft.get(0).getWidth()/3);
                stackLeft.get(0).setY(clown.getY()-stackLeft.get(0).getHeight()+1);
                for (int i = 1; i < stackLeft.size(); i++) {
                    stackLeft.get(i).setX(stackLeft.get(i - 1).getX());
                    stackLeft.get(i).setY(stackLeft.get(i - 1).getY() - 10);
                }
                if (intersectPlates(m, stackLeft.peek())) {
                 stackLeft.push(m);
                }
            }
            if (!stackRight.isEmpty()) {
                stackRight.get(0).setX(clown.getX()+(clown.getWidth()*115)/153-stackRight.get(0).getWidth()/3);
                stackRight.get(0).setY(clown.getY()-stackRight.get(0).getHeight()+1);
                for (int i = 1; i < stackRight.size(); i++) {
                    stackRight.get(i).setX(stackRight.get(i - 1).getX());
                    stackRight.get(i).setY(stackRight.get(i - 1).getY() - 10);
                }
                if (intersectPlates(m, stackRight.peek())) {
                 stackRight.push(m);
                }
            }
            if (stackLeft.size() >= 3) {
                Shapes object1 = (Shapes) stackLeft.get(stackLeft.size() - 1);
                Shapes object2 = (Shapes) stackLeft.get(stackLeft.size() - 2);
                Shapes object3 = (Shapes) stackLeft.get(stackLeft.size() - 3);
                if (object1.getId() == object2.getId() && object1.getId() == object3.getId()) {
                    score += 10;
                    for (int i = 0; i < 3; i++) {
                        stackLeft.peek().setY(-1 * (int) (Math.random() * getHeight()));
                        stackLeft.peek().setX((int) (Math.random() * getWidth()));
                        stackLeft.pop();
                    }
                }
            }
            if (stackRight.size() >= 3) {
                Shapes object1 = (Shapes) stackRight.get(stackRight.size() - 1);
                Shapes object2 = (Shapes) stackRight.get(stackRight.size() - 2);
                Shapes object3 = (Shapes) stackRight.get(stackRight.size() - 3);
                if (object1.getId() == object2.getId() && object1.getId() == object3.getId()) {
                    score += 10;
                    for (int i = 0; i < 3; i++) {
                        stackRight.peek().setY(-1 * (int) (Math.random() * getHeight()));
                        stackRight.peek().setX((int) (Math.random() * getWidth()));
                        stackRight.pop();
                    }
                }
            }
            if (m.getY() > getHeight()) {
                /* Falling object has reached the ground reuse it */
                m.setY(-1 * (int) (Math.random() * getHeight()));
                m.setX((int) (Math.random() * getWidth()));
            }  
            for (int i = 0; i < stackLeft.size(); i++) {
                    Shapes object = (Shapes) stackLeft.get(i);
                    if (object.getId() == 3) {
                        System.out.println(stackLeft.size());
                        for (int j = 0 ; !stackLeft.isEmpty() ; j++) {  
                            System.out.println(j+" popped");
                            stackLeft.peek().setY(-1 * (int) (Math.random() * getHeight()));
                            stackLeft.peek().setX((int) (Math.random() * getWidth()));
                            stackLeft.pop();
                        }
                    }
                }   
            for (int i = 0; i < stackRight.size(); i++) {
                    Shapes object = (Shapes) stackRight.get(i);
                    if (object.getId() == 3) {
                        System.out.println(stackRight.size());
                        for (int j = 0 ; !stackRight.isEmpty() ; j++) {  
                            System.out.println(j+" popped");
                            stackRight.peek().setY(-1 * (int) (Math.random() * getHeight()));
                            stackRight.peek().setX((int) (Math.random() * getWidth()));
                            stackRight.pop();
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
