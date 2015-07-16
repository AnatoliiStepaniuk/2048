package com.twenty48.Classes;

public class Score {
    private int currentScore;
    private int bestScore;

    public int getCurrentScore() {
        return currentScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setScore(int score) {
        currentScore = score;
        if(currentScore > bestScore)
            bestScore = currentScore;
    }
}
