/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameController {

    private final int screenWidth;
    private final int screenHeight;
    private int score = 0;
    private String heart = "♥";
    private String lives;
    private static GameController instance = null;

    private GameController(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.lives = heart.repeat(3);
    }

    public static synchronized GameController getInstance(int screenWidth, int screenHeight) {
        if (instance == null) {
            instance = new GameController(screenWidth, screenHeight);
        }
        return instance;
    }

    public String[] getShapes() {
        String[] shapes = new String[5];
        shapes[0] = "RedPlate";
        // shapes[0] = "OrangePlate";
        shapes[1] = "GreenPlate";
        shapes[2] = "BluePlate";
        //  shapes[3] = "PinkPlate";
        shapes[3] = "YellowPlate";
        shapes[4] = "OrangePlate";
        //shapes[5] = "RedPlate";
        return shapes;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public String getLives() {
        return lives;
    }

    public void setLives(int n) {
        this.lives = heart.repeat(n);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void update(GameObject m) {
        if (m.getY() > screenHeight) {
            m.setY(-1 * (int) (Math.random() * screenHeight));
            m.setX((int) (Math.random() * screenWidth));
        }
    }

    public boolean isLost(StackController stackController) {
        if (stackController.getLeftPeek() <= 100 && stackController.getLeftPeek() != 0) {
            return true;
        }
        if (stackController.getRightPeek() <= 100 && stackController.getRightPeek() != 0) {
            return true;
        }
        if (stackController.getMovingSize() == stackController.getStacksSize()) {
            return true;
        }
        if (getLives().length() == 0) {
            return true;
        }

        return false;
    }
}
