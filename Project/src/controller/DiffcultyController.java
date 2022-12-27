package controller;

import view.Game;

public class DiffcultyController {

    public Game newGame(String diffculty, int width, int height)
    {
        Game game = null;
        if(diffculty == "Easy")
        {
            game=new Game(width, height,50 , "./images/Easy.png");
        }else if(diffculty == "Normal")
        {
            game=new Game(width, height,20 , "./images/Normal.png");
        }else if(diffculty == "Hard")
        {
            game=new Game(width, height,1 , "./images/Hard.png");
        }
        return game;
    }
}
