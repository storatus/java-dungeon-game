package text-based;

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


//Running tests

//PlayerScore bc = new PlayerScore("Bob", 0);
//
//System.out.println("Initial gold score: " + bc.showGoldScore());
//
//bc.addToGoldScore(200);
//
//System.out.println("Current gold score : " + bc.showGoldScore());
//bc.reduceGoldScore(100);
//System.out.println("Current gold score : " + bc.showGoldScore());
//Room entranceHall = new Room(8, 10);
////entranceHall.printRoom();
//System.out.println();
//
//entranceHall.setRoomGridLocation(3, 4,
//true);
//System.out.println(entranceHall.getRoomGridLocation(3, 4));
//entranceHall.printRoom();
//System.out.println();
//
//Room armoury = new Room (6, 14);
//armoury.printRoom();