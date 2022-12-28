package model;

public class EasyWorld implements Difficulty {
     private final int speedE = 30, movingE = 10, bombsE = 2;
    private final int gameSpeed, movingNum, bombsNum;
    private final String theme;

    public EasyWorld() {
        this.gameSpeed = speedE;
        this.movingNum = movingE;
        this.bombsNum = bombsE;
        this.theme="./images/Easy.png";
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
