package com.twenty48.Classes;

public class Score {
    private int currentScore;
    private int bestScore;

    public int getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(int score) {currentScore = score;}
    public void setScore(int score) {
        currentScore = score;
        if(currentScore > bestScore)
            bestScore = currentScore;
    }
}
