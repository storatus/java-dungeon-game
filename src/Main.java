//package com.SWEngCW2;

public class Main {

    public static void main(String[] args) {
    	
    	
     	
	PlayerScore bc = new PlayerScore("Bob", 0);

    System.out.println("Initial gold score: " + bc.showGoldScore());

	bc.addToGoldScore(200);

	System.out.println("Current gold score : " + bc.showGoldScore());
	bc.reduceGoldScore(100);
    System.out.println("Current gold score  : " + bc.showGoldScore());


    
    
    Room entranceHall = new Room(8, 10);
    //entranceHall.printRoom();
    System.out.println();

    entranceHall.setRoomGridLocation(3, 4,
            true);
    System.out.println(entranceHall.getRoomGridLocation(3, 4));
    entranceHall.printRoom();
    System.out.println();

    Room armoury = new Room (6, 14);
    armoury.printRoom();
    

    }
}
