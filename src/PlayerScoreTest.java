//package com.SWEngCW2;

/**
 * JUnit tests for Gold scoring system for
 * Multi-Player Adventure Game SWEngCW02. Testing
 * PlayerScore.java addToGoldScore(),  reduceGoldScore()
 * and showGoldScore() methods
 *
 * @version 1.0
 * @release 12/11/2017 Addressing Function Requirement FR2b, FR2hii, User Story
 *          US004
 * @See PlayerScore.java
 */

import com.SWEngCW2.PlayerScore;

import static org.junit.Assert.*;

public class PlayerScoreTest {

    // jUnit test getGoldScore() method
    @org.junit.Test
    public void getGoldScore() throws Exception {
        PlayerScore bc = new PlayerScore("Bob",
                150);
        int currentScore = bc.getGoldScore();
        assertEquals(150, currentScore, 0);
    }

    // jUnit test addTotGoldScore() method
    @org.junit.Test
    public void addToGoldScore() throws Exception {
        PlayerScore bc = new PlayerScore("Bob",
                0);
        int currentScore = bc.addToGoldScore(400);
        assertEquals(400, currentScore, 0);
    }

    // jUnit test reduceGoldScore() method
    @org.junit.Test
    public void reduceGoldScore() throws Exception {
        PlayerScore bc = new PlayerScore("Bob",
                1000);
        int currentScore = bc.reduceGoldScore(400);
        assertEquals(600, currentScore, 0);

    }

    jUnit test showGoldScore() method
    @org.junit.Test
    public void showGoldScore() throws Exception {
        PlayerScore sc = new PlayerScore("Sanjeev",
                1000);
        int currentScore = sc.showGoldScore();
        assertEquals(1000, currentScore, 0);
    }


}