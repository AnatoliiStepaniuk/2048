package com.twenty48;

public class Game {

    private GameState gameState;
    private PlayField playField;

    public Game(GameState gameState){
        this.gameState = gameState;
        playField = new PlayField();
    }

/*    public static void main(String[] args) {
        Game game = new Game(GameState.Game);
        System.out.println(game.run(2));
        System.out.println(game.run(1));
        System.out.println(game.run(1));
        System.out.println(game.run(1));
    }*/


    public String run(int code){

        Direction.direction = "NO";
        Direction.dX = 0;
        Direction.dY = 0;
        switch (code){
            case 0:
                return playField.getTiles();
            case 1:
                Direction.direction = "UP";
                Direction.dX = 0;
                Direction.dY = -1;
                break;
            case 2:
                Direction.direction = "RIGHT";
                Direction.dX = 1;
                Direction.dY = 0;
                break;
            case 3:
                Direction.direction = "DOWN";
                Direction.dX = 0;
                Direction.dY = 1;
                break;
            case 4:
                Direction.direction = "LEFT";
                Direction.dX = -1;
                Direction.dY = 0;
                break;
            default:
                ///
                break;
              // если код 0 - новая игра, то что?
        }

        switch (gameState){
            case Game:
                playField.oneTurn();
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

        return playField.getTiles();
    }

    private GameState getGameState(){

        if (!playField.actionIsPossible())
            return GameState.GameOver;
/*
        else
           if (playField.getMaxValue() == 2048)
             return GameState.Twenty48;
*/
            else
               return GameState.Game;

    }
}
