package com.twenty48;

public class Game {

    private GameState gameState;
    private PlayField playField;

    public Game(GameState gameState){
        this.gameState = gameState;
        playField = new PlayField();
    }

    public String run(String direction){

        setDirection(direction);
        switch (gameState){
            case NewGame:
                gameState = GameState.Game;
                return playField.getTiles();
            case Game:
                playField.oneTurn();
                break;
            case GameOver:
                ///
                break;
            case Twenty48:
                ///
                break;
        }

        gameState = getGameState();

        return playField.getTiles();
    }

    private void setDirection(String direction) {

        switch (direction) { // refactor! someFunc(String, int, int)
            case "NewGame":
                gameState = GameState.NewGame;
                break;
            case "UP":
                Direction.direction = "UP";
                Direction.dX = 0;
                Direction.dY = -1;
                break;
            case "RIGHT":
                Direction.direction = "RIGHT";
                Direction.dX = 1;
                Direction.dY = 0;
                break;
            case "DOWN":
                Direction.direction = "DOWN";
                Direction.dX = 0;
                Direction.dY = 1;
                break;
            case "LEFT":
                Direction.direction = "LEFT";
                Direction.dX = -1;
                Direction.dY = 0;
                break;
            default:
                break;
        }
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
