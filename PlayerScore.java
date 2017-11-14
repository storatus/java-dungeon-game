package com.SWEngCW2;

public class PlayerScore {

    private String playerName;
    private int goldScore;

    public PlayerScore(String playerName, int goldScore) {
        this.playerName = playerName;
        this.goldScore = goldScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGoldScore() {
        return goldScore;
    }

    public int addToGoldScore(int addGoldAmount) {
        goldScore += addGoldAmount;
        return goldScore;
    }

    public int reduceGoldScore (int reduceGoldAmount) {
        goldScore -= reduceGoldAmount;
        return goldScore;
    }

    public int showGoldScore() {
        return goldScore;
    }


}
