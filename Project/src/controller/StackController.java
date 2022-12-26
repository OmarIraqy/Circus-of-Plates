package controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import model.*;

public class StackController {

    private int screenWidth;
    private int screenHeight;
    private GameObject clown;
    private Stack<GameObject> leftStack = new Stack<>();
    private Stack<GameObject> rightStack = new Stack<>();
    private static StackController instance = null;
    private HashMap<Integer, Stack<GameObject>> stack;
    private Iterator<HashMap.Entry<Integer, Stack<GameObject>>> entries;

    private StackController(int screenWidth, int screenHeight, GameObject clown) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.clown = clown;
        this.stack = new HashMap<>();
        stack.put(1, leftStack);
        stack.put(2, rightStack);
    }

    public static synchronized StackController getInstance(int screenWidth, int screenHeight, GameObject clown) {
        if (instance == null) {
            instance = new StackController(screenWidth, screenHeight, clown);
        }
        return instance;
    }

    public void update(GameObject o) {
        if (leftStack.search(o) == -1 || rightStack.search(o) == -1) {
            o.setY((o.getY() + 1));
        }
        refactorShapes(o);
    }

    public void addToStack(GameObject o, int key) {
        stack.get(key).push(o);
    }

    private boolean intersectStacks(GameObject o1, GameObject o2) {
        return (o1.getHeight() + o1.getY() == o2.getY() && o1.getX() + o1.getWidth() / 2 <= o2.getX() + (o2.getWidth() * 3) / 4 && o1.getX() + o1.getWidth() / 2 >= o2.getX() + o2.getWidth() * 0.25);
    }

    public void refactorShapes(GameObject m) {
        entries = stack.entrySet().iterator();
        HashMap.Entry<Integer, Stack<GameObject>> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            if (!entry.getValue().isEmpty()) {
                if (entry.getKey() == 1) {
                    handleLeftStack(m);
                } else {
                    handleRightStack(m);
                }
            }
        }
    }

    public void handleLeftStack(GameObject m) {
        leftStack.get(0).setX(clown.getX() - leftStack.get(0).getWidth() / 3);
        leftStack.get(0).setY(clown.getY() - leftStack.get(0).getHeight() + 1);
        for (int i = 1; i < leftStack.size(); i++) {
            leftStack.get(i).setX(leftStack.get(i - 1).getX());
            leftStack.get(i).setY(leftStack.get(i - 1).getY() - 10);
        }
        if (intersectStacks(m, leftStack.peek())) {
            leftStack.push(m);
        }
    }

    public void handleRightStack(GameObject m) {
        rightStack.get(0).setX(clown.getX() + (clown.getWidth() * 115) / 153 - rightStack.get(0).getWidth() / 3);
        rightStack.get(0).setY(clown.getY() - rightStack.get(0).getHeight() + 1);
        for (int i = 1; i < rightStack.size(); i++) {
            rightStack.get(i).setX(rightStack.get(i - 1).getX());
            rightStack.get(i).setY(rightStack.get(i - 1).getY() - 10);
        }
        if (intersectStacks(m, rightStack.peek())) {
            rightStack.push(m);
        }
    }

    private void modifyLast3(int key) {
        for (int i = 0; i < 3; i++) {
            stack.get(key).peek().setY(-1 * (int) (Math.random() * screenHeight));
            stack.get(key).peek().setX((int) (Math.random() * screenWidth));
            stack.get(key).pop();
        }
    }

    public boolean verify() {
        int flags = 0;
        entries = stack.entrySet().iterator();
        HashMap.Entry<Integer, Stack<GameObject>> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            if (entry.getValue().size() >= 3) {
                Shapes object1 = (Shapes) entry.getValue().get(entry.getValue().size() - 1);
                Shapes object2 = (Shapes) entry.getValue().get(entry.getValue().size() - 2);
                Shapes object3 = (Shapes) entry.getValue().get(entry.getValue().size() - 3);
                if (object1.getId() == object2.getId() && object1.getId() == object3.getId()) {
                    modifyLast3(entry.getKey());
                    flags++;
                }
            }
        }
        return flags > 0;
    }

    public boolean handleBomb() {
        int flags = 0;
        entries = stack.entrySet().iterator();
        HashMap.Entry<Integer, Stack<GameObject>> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            for (int i = 0; i < entry.getValue().size(); i++) {
                Shapes object = (Shapes) entry.getValue().get(i);
                if (object.getId() == 100) {
                    for (int j = 0; !entry.getValue().isEmpty(); j++) {
                        entry.getValue().peek().setY(-1 * (int) (Math.random() * screenHeight));
                        entry.getValue().peek().setX((int) (Math.random() * screenWidth));
                        entry.getValue().pop();
                    }
                    flags++;
                }
            }
        }
        return flags > 0;
    }

    public boolean isLeftEmpty() {
        return leftStack.isEmpty();
    }

    public boolean isRightEmpty() {
        return rightStack.isEmpty();
    }

}
