/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameController {

    private int screenWidth;
    private int screenHeight;
    public static GameController instance = null;

    private GameController(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public static synchronized GameController getInstance(int screenWidth, int screenHeight) {
        if (instance == null) {
            instance = new GameController(screenWidth, screenHeight);
        }
            return instance;
    }

    public void update(GameObject m) {
        if (m.getY() > screenHeight) {
            m.setY(-1 * (int) (Math.random() * screenHeight));
            m.setX((int) (Math.random() * screenWidth));
        }
    }

}
