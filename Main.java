package com.SWEngCW2;

public class Main {

    public static void main(String[] args) {
	PlayerScore bc = new PlayerScore("Bob", 0);

    System.out.println("Initial gold score: " + bc.showGoldScore());

	bc.addToGoldScore(200);

	System.out.println("Current gold score : " + bc.showGoldScore());
	bc.reduceGoldScore(100);
    System.out.println("Current gold score  : " + bc.showGoldScore());
    System.out.println(bc.getPlayerName());



    }
}
