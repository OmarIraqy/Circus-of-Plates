package controller;

import view.Game;

public class DiffcultyController {

    public Game newGame(String diffculty, int width, int height)
    {
        Game game = null;
        if(diffculty == "Easy")
        {
            game=new Game(width, height,50 , "");
        }else if(diffculty == "Normal")
        {
            game=new Game(width, height,20 , "");
        }else if(diffculty == "Hard")
        {
            game=new Game(width, height,10 , "");
        }
        return game;
    }
}
