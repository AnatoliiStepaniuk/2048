package com.twenty48.Classes;

public class Game {

    private GameState gameState;
    private PlayField playField;

    public Game(GameState gameState){
        this.gameState = gameState;
        playField = new PlayField();
    }

    public void moveTiles(String direction) {

        setDirection(direction);
        switch (gameState){
            case Game:
                playField.moveTiles();
                break;
            case GameOver:
                ///
                break;
            case Twenty48:
                ///
                break;
        }

        gameState = getGameState();
    }

    private void setDirection(String direction) {

        switch (direction) {
            case "UP":
                Direction.setDirection("UP", 0, -1);
                break;
            case "RIGHT":
                Direction.setDirection("RIGHT", 1, 0);
                break;
            case "DOWN":
                Direction.setDirection("DOWN", 0, 1);
                break;
            case "LEFT":
                Direction.setDirection("LEFT", -1, 0);
                break;
            default:
                break;
        }
    }

    public GameState getGameState(){

        if (!playField.actionIsPossible())
            return GameState.GameOver;
        else
           if (playField.getMaxValue() == 2048)
             return GameState.Twenty48;
           else
               return GameState.Game;
    }

    public String getTiles() {
        return playField.getTiles();
    }

    public int getScoreIncrease() {
        return playField.getScoreIncrease();
    }
}
