/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameController {

    private int screenWidth;
    private int screenHeight;

    public GameController(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(GameObject m) {
        if (m.getY() > screenHeight) {
            m.setY(-1 * (int) (Math.random() * screenHeight));
            m.setX((int) (Math.random() * screenWidth));
        }
    }

}
