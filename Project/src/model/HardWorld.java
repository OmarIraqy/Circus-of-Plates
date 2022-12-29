package model;


public class HardWorld implements Difficulty {

    private final int speedH = 1, movingH = 25, bombsH = 4;
    private final int gameSpeed, movingNum, bombsNum;
    private final String theme;

    public HardWorld() {
        this.gameSpeed = speedH;
        this.movingNum = movingH;
        this.bombsNum = bombsH;
        this.theme = "./images/Hard.png";
    }

    @Override
    public int getGameSpeed() {
        return gameSpeed;
    }

    @Override
    public int getMovingNum() {
        return movingNum;
    }

    @Override
    public int getBombsNum() {
        return bombsNum;
    }

    @Override
    public String getTheme() {
        return this.theme;
    }
}
