
/**
 * Class. Room in Dungeon for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 2.0
 * @created 14/11/2017
 * @release 17/11/2017 Addressing Function Requirement
 * FR2a, FR2b, User Story US001
 * @See PlayerScore.java, Player.java, PlayerTest.java
 */

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String roomName;
    private int length;
    private int width;
    private int door_x_Coordinate;
    private int door_y_Coordinate;
    private int trapdoor_x_Coordinate;
    private int trapdoor_y_Coordinate;
    private boolean[][] roomArrayGrid;
    Coordinates doorCoordinates;
    Bot bot;
    Player player;

    private Map<Room, Coordinates> doors;

    public Room(String roomName, int length, int width) {

        this.roomName = roomName;
        this.length = length;
        this.width = width;
        this.roomArrayGrid = new boolean[length][width];
        // initialise each grid cell to zero
        for(int i=0; i<length; ++i) {
            for(int j=0; j<width; ++j) {
                this.roomArrayGrid[i][j] = false;
            }
        }

        this.doors = new HashMap<Room, Coordinates>();
    }

    public void addDoor(Room nextRoom, Coordinates coordinates) {
        this.doors.put(nextRoom, coordinates);
    }

    public Map<Room, Coordinates> getDoors() {
        return doors;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDoor_x_Coordinate() {
        return door_x_Coordinate;
    }

    public void setDoor_x_Coordinate(int door_x_Coordinate) {
        this.door_x_Coordinate = door_x_Coordinate;
    }

    public int getDoor_y_Coordinate() {
        return door_y_Coordinate;
    }

    public void setDoor_y_Coordinate(int door_y_Coordinate) {
        this.door_y_Coordinate = door_y_Coordinate;
    }

    public int getTrapdoor_x_Coordinate() {
        return trapdoor_x_Coordinate;
    }

    public void setTrapdoor_x_Coordinate(int trapdoor_x_Coordinate) {
        this.trapdoor_x_Coordinate = trapdoor_x_Coordinate;
    }

    public int getTrapdoor_y_Coordinate() {
        return trapdoor_y_Coordinate;
    }

    public void setTrapdoor_y_Coordinate(int trapdoor_y_Coordinate) {
        this.trapdoor_y_Coordinate = trapdoor_y_Coordinate;
    }





    public void setRoomGridLocation(int x, int y,
                                       boolean locationBooleanValue) {
        this.roomArrayGrid[x][y] = locationBooleanValue;
    }



    public boolean getRoomGridLocation(int x, int y)
    {

        return this.roomArrayGrid[x][y];
    }

    public void printRoom() {
        for(int i=0; i<length; ++i) {
            for(int j=0; j<width; ++j) {
                if(roomArrayGrid[i][j] == true){
                    System.out.print('X' + " ");
                }
                else {
                    System.out.print('_' + " ");
                }
            }
            System.out.println();

        }
    }

    public void printRoomWithBot() {
        for(int i=0; i<length; ++i) {
            for(int j=0; j<width; ++j) {
                if(( i == bot.getX_Coordinate_Bot() ) && (j == bot.getY_Coordinate_Bot())) {
                    roomArrayGrid[i][j] = true;
                    System.out.print('$' + " ");
                }
                if (i == player.getX_Coordinate_Player() &&
                        j == player.getY_Coordinate_Player() ) {
                    //roomArrayGrid[i][j] = true;
                    System.out.println('X' + " ");
                }
                else {
                    System.out.print('_' + " ");
                }
            }
            System.out.println();

        }
    }
}