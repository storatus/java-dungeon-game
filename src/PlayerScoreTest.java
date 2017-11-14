//package com.SWEngCW2;

import static org.junit.Assert.*;

public class PlayerScoreTest {

    @org.junit.Test
    public void getGoldScore() throws Exception {
        PlayerScore sc = new PlayerScore("Sanjeev",
                150);
        int currentScore = sc.getGoldScore();
        assertEquals(150, currentScore, 0);
    }

    @org.junit.Test
    public void addToGoldScore() throws Exception {
        PlayerScore sc = new PlayerScore("Sanjeev",
                0);
        int currentScore = sc.addToGoldScore(400);
        assertEquals(400, currentScore, 0);
    }

    @org.junit.Test
    public void reduceGoldScore() throws Exception {
        PlayerScore sc = new PlayerScore("Sanjeev",
                1000);
        int currentScore = sc.reduceGoldScore(400);
        assertEquals(600, currentScore, 0);

    }

    @org.junit.Test
    public void showGoldScore() throws Exception {
        PlayerScore sc = new PlayerScore("Sanjeev",
                1000);
        int currentScore = sc.showGoldScore();
        assertEquals(1000, currentScore, 0);
    }


}