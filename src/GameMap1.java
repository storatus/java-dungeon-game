import java.util.HashMap;
import java.util.Map;

/**
 * Class. Hashmap structure storing room data and door coordinates
 *   for Multi-Player Adventure Game SWEngCW02
 * @version 1.0
 * @release 28/11/2017
 * Addressing Function Requirement FR2.a. FR2.c, User Story US017
 * @See Room.java, Player.java, MainPlayerRoomMoveChange.java,
 * PlayerScore.java
 */

public class GameMap1 {


    private static Map<Integer, Room> roomNetwork =
            new HashMap<>();

    public static void main(String[] args) {


        roomNetwork.put(1, (new Room(1,
                "Entry Cavern", 10, 8)));
        roomNetwork.put(2, (new Room(2, "Dare to Tread",
                12, 6)));
        roomNetwork.put(3, (new Room(3,
                "Halloween Horror", 12, 8)));
        roomNetwork.put(4, (new Room(4,
                "Asylum", 15, 8)));
        roomNetwork.put(5, (new Room(5,
                "Memories of the Past", 7, 12)));
        roomNetwork.put(6, (new Room(6,
                "Dead Souls", 7, 15)));
        roomNetwork.put(7, (new Room(7,
                "Ghost Town", 6, 11)));
        roomNetwork.put(8, (new Room(8,
                "Pit of Despair", 24, 9)));
        roomNetwork.put(9, (new Room(9,
                "Inferno", 4, 17)));
        roomNetwork.put(10, (new Room(10,
                "Last Dance", 6, 13)));
        roomNetwork.put(11, (new Room(11,
                "Escape Passage", 30, 4)));


        roomNetwork.get(1).addDoor(1,
                new Coordinates(3,9));

        roomNetwork.get(2).addDoor(1, new
                Coordinates(0, 7));
        roomNetwork.get(2).addDoor(3, new
                Coordinates(6, 9));


        roomNetwork.get(3).addDoor(2, new
                Coordinates(3, 0));
        roomNetwork.get(3).addDoor(3, new
                Coordinates(8, 4));
        roomNetwork.get(3).addDoor(3, new
                Coordinates(8, 11));

        roomNetwork.get(4).addDoor(1, new
                Coordinates(3, 15));
        roomNetwork.get(4).addDoor(2, new
                Coordinates(6, 0));

        roomNetwork.get(5).addDoor(1, new
                Coordinates(6, 7));
        roomNetwork.get(5).addDoor(3, new
                Coordinates(12, 1));

        roomNetwork.get(6).addDoor(4, new
                Coordinates(0, 1));
        roomNetwork.get(6).addDoor(1, new
                Coordinates(15, 6));

        roomNetwork.get(7).addDoor(1, new
                Coordinates(9, 6));


        roomNetwork.get(8).addDoor(2, new
                Coordinates(7, 0));
        roomNetwork.get(8).addDoor(4, new
                Coordinates(0, 21));

        roomNetwork.get(9).addDoor(4, new
                Coordinates(0, 3));


        roomNetwork.get(10).addDoor(4, new
                Coordinates(0, 6));
        roomNetwork.get(10).addDoor(3, new
                Coordinates(13, 3));

        roomNetwork.get(11).addDoor(3, new
                Coordinates(0, 3));

//////////////////////////////////////////////////////////////////////////
        // Change room ID below to see profile of each Room with
        // dimensions and door exits

        int roomID = 7;
        System.out.println(roomNetwork.get(roomID).getRoomName());
        System.out.println(roomNetwork.get(roomID).getRoomID());
        System.out.println("Room x length : " +
                roomNetwork.get(roomID).getX_length());
        System.out.println("Room y length : " +
                roomNetwork.get(roomID).getY_length());

        Map<Integer, Coordinates> doors = roomNetwork.get(roomID).getDoors();
        System.out.println("Doors to this room in directions : ");
        for (Integer door : doors.keySet()) {
            System.out.println(door + "");
        }
        System.out.println();

        // Screen indicates available doors from this room
        // change direction below to see door location
        int direction = 2;

        if(doors.containsKey(direction)) {

            System.out.println("Door x coordinate: " + doors.get(direction).getX_Coordinate());
            System.out.println("Door y coordinate: " + doors.get(direction).getY_Coordinate());

        } else {
            System.out.println("There is no door in that direction.");
        }

        Room currentRoom = roomNetwork.get(roomID);
        currentRoom.printRoom();
////////////////////////////////////////////////////////////////////////////////

















    }
}
