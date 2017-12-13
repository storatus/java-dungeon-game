/**
 * Class. Gold Scoring System for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 1.0
 @ gitCommit 1.1
 * @release 12/11/2017 Addressing Function Requirement FR2b, FR2hii,
 *    User Story US004
 * @See PlayerScoreTest.java
 */

package com.SWEngCW2;

/**
 * Class. Gold scoring system for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 1.0
 * @release 12/11/2017 Addressing Function Requirement FR2b, FR2hii, User Story
 *          US004
 * @See PlayerScoreTest.java
 */

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

    // increments score when player finds gold
    public int addToGoldScore(int addGoldAmount) {
        goldScore += addGoldAmount;
        return goldScore;
    }

    // decrements score when player finds gold
    public int reduceGoldScore (int reduceGoldAmount) {
        goldScore -= reduceGoldAmount;
        return goldScore;
    }

    public int showGoldScore() {
        return goldScore;
    }


}
