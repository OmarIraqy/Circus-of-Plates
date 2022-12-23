package Main;

import eg.edu.alexu.csd.oop.game.GameEngine;
import view.Game;

public class Main {

    public static void main(String[] args) {

        GameEngine.start("Test Game", new Game(600, 600));

    }
}
