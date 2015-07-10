package com.twenty48;

import javax.servlet.http.HttpSession;

public class Game {

    private GameState gameState;
    private PlayField playField;

    public Game(GameState gameState){
        this.gameState = gameState;
        playField = new PlayField();
    }

    public String run(int code){

        Direction dir = Direction.NO;
        switch (code){
            case 0:
                return playField.getCurrentField();
            case 1:
                dir = Direction.UP;
                break;
            case 2:
                dir = Direction.RIGHT;
                break;
            case 3:
                dir = Direction.DOWN;
                break;
            case 4:
                dir = Direction.LEFT;
                break;
            default:
                ///
                break;
              // если код 0 - новая игра, то что?
        }

        switch (gameState){
            case Game:
                playField.oneTurn(dir);
                break;
            case GameOver:
                ///
                break;
            case Twenty48:
                ///
                break;
            case SuperGame:
                ///
                break;
            default:
                ///
                break;

        }

        gameState = getGameState();
        return playField.getCurrentField();
    }

    private GameState getGameState(){

        if (!playField.actionIsPossible())
            return GameState.GameOver;
        else
           if (playField.getMaxValue() == 2048)
             return GameState.Twenty48;
            else
               return GameState.Game;

    }
}
