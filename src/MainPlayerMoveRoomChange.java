

import java.util.Scanner;

public class MainPlayerMoveRoomChange {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        
        Room entranceHall = new
                Room("EntranceHall", 8, 10);
        //entranceHall.printRoom();
        System.out.println("Entrancehall, length : " + entranceHall.getLength() +" width : " + entranceHall.getWidth());
        System.out.println();

        /* Code to peek at boolean status at a location

        entranceHall.setRoomGridLocation(3, 4,
                false);
        System.out.println(entranceHall.getRoomGridLocation(3, 4));
        //entranceHall.printRoom();
        System.out.println();

        */

        Room armoury = new Room ("Armoury", 6, 14);
        //armoury.printRoom();

        Player bilbo = new Player(entranceHall, 5, 5);
        // insert bilbo location in room
        bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                bilbo.getY_Coordinate_Player(), true);
        entranceHall.printRoom();
        System.out.println("x location : " + bilbo.getX_Coordinate_Player());
        System.out.println("y location : " + bilbo.getY_Coordinate_Player());

        // setting coordinates of door and trapdoor
        // in entrance hall
        entranceHall.setTrapdoor_x_Coordinate(5);
        entranceHall.setTrapdoor_y_Coordinate(4);
        entranceHall.setDoor_x_Coordinate(3);
        entranceHall.setDoor_y_Coordinate(9);


        //System.out.println("Trapdoor x coord " +
                //entranceHall.getTrapdoor_x_Coordinate());
       // System.out.println("Trapdoor y coord " +
                //entranceHall.getTrapdoor_y_Coordinate());




/* start of code to read user keyboard entry
* prints instructions
* moves player
* enables user to move to another room
*/
        boolean quit = false;
        /* 
         * new method added for a start message
         */
        mainMenu();
        printInstructions();
        while(!quit) {
            int nextMove = scanner.nextInt();
            scanner.nextLine();

            switch(nextMove) {
                case 0:
                    System.out.println("Exit Game. Goodbye.");
                    quit = true;
                    break;

                case 1:
                    System.out.println("Player moves " +
                            "in rightwards direction.");
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), false);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), false);
                    bilbo.moveRight();   // MOVES PLAYER RIGHT
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), true);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), true);
                    break;

                case 2:
                    System.out.println("Player moves " +
                            "in leftwards direction");
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), false);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), false);
                    bilbo.moveLeft();  // MOVES PLAYER LEFT
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), true);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), true);
                    break;

                case 3:
                    System.out.println("Player moves " +
                            "in downwards direction");
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), false);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), false);
                    bilbo.moveDown();   // MOVES PLAYER DOWN
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), true);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), true);
                    //entranceHall.printRoom();
                    break;

                case 4:
                    System.out.println("Player moves " +
                            "in upwards direction");
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), false);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                           // bilbo.getY_Coordinate_Player(), false);
                    bilbo.moveUp();   // MOVES PLAYER UP
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), true);
                    //entranceHall.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), true);
                    break;

                case 5:
                    System.out.println("You have stepped through the " +
                            "door to the armoury");
                    bilbo.setCurrentRoom(armoury);
                    //armoury.setRoomGridLocation(3, 0,
                            //true);
                    bilbo.setX_Coordinate_Player(3);
                    bilbo.setY_Coordinate_Player(0);
                    bilbo.currentRoom.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            bilbo.getY_Coordinate_Player(), true);
                    //armoury.setRoomGridLocation(bilbo.getX_Coordinate_Player(),
                            //bilbo.getY_Coordinate_Player(), true);
                    //bilbo.currentRoom.printRoom();
                    System.out.println("x location : " + bilbo.getX_Coordinate_Player());
                    System.out.println("y location : " + bilbo.getY_Coordinate_Player());
                    break;

                case 6:
                    printInstructions();
                    break;
               /*
                * code added for a main menu
                */
                case 7:
                	printInstructions();
                case 8:
                	System.out.println("*** Need to add highscore function *** ");
                case 9:
                	System.out.println("*** Need to invoke game, do not know how to do this ***");

            }

            bilbo.currentRoom.printRoom();
            //System.out.println("x coordinate : "
                   // + bilbo.getX_Coordinate_Player() + "y coordinate : " +
                  // bilbo.getY_Coordinate_Player());

            if(bilbo.getX_Coordinate_Player() ==
                    bilbo.currentRoom.getTrapdoor_x_Coordinate() && bilbo.getY_Coordinate_Player() ==
                    bilbo.currentRoom.getTrapdoor_y_Coordinate()) {
                System.out.println("You have discovered a trapdoor. " +
                        "How do you wish to proceed to the room below?");
            }

            if(bilbo.getX_Coordinate_Player() ==
                    bilbo.currentRoom.getDoor_x_Coordinate() &&
                    bilbo.getY_Coordinate_Player() ==
                    bilbo.currentRoom.getDoor_y_Coordinate()) {
                System.out.println("You have discovered a door. " +
                        "Hit 5 to go through the door?");
            }

        }
        }

    

    private static void printInstructions() {
        System.out.println("\nChoose your next move.\nHit:");
        System.out.println("0  - to exit game\n" +
                "1  - to move player right\n" +
                "2  - to move player left\n" +
                "3  - to move player down\n" +
                "4  - to move player up\n" +
                "5  - to exit through door\n" +
                "6  - to print a list of options.");
        System.out.println("Make your selection: ");
    }
    
    private static void mainMenu() {
        System.out.println("\nWelcome to Bob's Dungeon.\nHit:");
        System.out.println("0  - if you are too scared to enter\n" +
                "7 - for instructions on how to play and background on your mission\n" +
                "8 - for high scores\n" +
                "9  - to play\n" );

        System.out.println("Make your selection: ");
    }
}