package controller;

import model.Difficulty;
import model.EasyWorld;
import model.HardWorld;
import model.NormalWorld;

public class DiffcultyController {

    public Difficulty getDifficulty(String type) {

        if (type.equalsIgnoreCase("Easy")) {
            return new EasyWorld();
        } else if (type.equalsIgnoreCase("Normal")) {
            return new NormalWorld();
        } else {
            return new HardWorld();
        }
    }
}
