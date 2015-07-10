package com.mywebapp.getpost;

public class Score {
    private static int currentScore = 0;
    private static int bestScore = 0;

    public static int getCurrentScore() {
        return currentScore;
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static void setScore(int score) {
        currentScore = score;
        if(currentScore > bestScore)
            bestScore = currentScore;
    }
}
