
package model;

public class NormalWorld implements Difficulty {

    private final int speedN = 15, movingN = 15, bombsN = 3;
    private final int gameSpeed, movingNum, bombsNum;
    private final String theme;

    public NormalWorld() {
        this.gameSpeed = speedN;
        this.movingNum = movingN;
        this.bombsNum = bombsN;
        this.theme = "./images/Normal.png";
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
