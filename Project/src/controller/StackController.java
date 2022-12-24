package controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Stack;
import model.*;

public class StackController {

    private int screenWidth;
    private int screenHeight;
    private GameObject clown;
    private Stack<GameObject> stack = new Stack<>();

    public StackController(int screenWidth, int screenHeight, GameObject clown) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.clown = clown;
    }

    public void update(GameObject o) {
        if (stack.search(o) == -1) {
            o.setY((o.getY() + 1));
        }
        refactorShapes();
    }

    public void addToStack(GameObject o) {
        stack.push(o);
    }

    public boolean searchStack(GameObject o) {
        if (stack.search(o) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void refactorShapes() {
        if (!stack.isEmpty()) {
            stack.get(0).setX(clown.getX());
            stack.get(0).setY(clown.getY() - 2);
            for (int i = 1; i < stack.size(); i++) {
                stack.get(i).setX(stack.get(i - 1).getX());
                stack.get(i).setY(stack.get(i - 1).getY() - 10);
            }
        }
    }

    private void modifyLast3() {
        for (int i = 0; i < 3; i++) {
            stack.peek().setY(-1 * (int) (Math.random() * screenHeight));
            stack.peek().setX((int) (Math.random() * screenWidth));
            stack.pop();
        }
    }

    public boolean verify() {
        if (stack.size() >= 3) {
            Shapes object1 = (Shapes) stack.get(stack.size() - 1);
            Shapes object2 = (Shapes) stack.get(stack.size() - 2);
            Shapes object3 = (Shapes) stack.get(stack.size() - 3);
            if (object1.getId() == object2.getId() && object1.getId() == object3.getId()) {
                modifyLast3();
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean handleBomb() {
        for (int i = 0; i < stack.size(); i++) {
            Shapes object = (Shapes) stack.get(i);
            if (object.getId() == 3) {
                for (int j = 0; !stack.isEmpty(); j++) {
                    stack.peek().setY(-1 * (int) (Math.random() * screenHeight));
                    stack.peek().setX((int) (Math.random() * screenWidth));
                    stack.pop();
                }
                return true;
            }
        }
        return false;
    }
}
