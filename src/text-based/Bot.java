
/**
 * Class. 'Bot Player for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 2.0
 * @created 27/11/2017
 * @release 1/12/2017 Addressing  Functional
 *      Requirement FR1b, FR2g, User Story US0017,
 *      SD07
 * @See BotTest.java, PlayerScore.java, Player.java, PlayerTest.java,
 * MainPlayerMoveRoomChange.java
 */


public class Bot {

    private int x_Coordinate_Bot;
    private int y_Coordinate_Bot;
    Room currentRoom;



    // Constructor. Initialises currentRoom parameter
    // for 'bot instance and sets initial coordinates
    public Bot(Room currentRoom, int x_Coordinate_Bot,
               int y_Coordinate_Bot) {
        this.x_Coordinate_Bot = x_Coordinate_Bot;
        this.y_Coordinate_Bot = y_Coordinate_Bot;
        this.currentRoom = currentRoom;
    }

    // Randomise movement of the 'bot within the length of the room
    public int randomiseX_Coordinate() {
        double randomX_Coordinate = Math.random() *
                currentRoom.getLength()-1;
        this.x_Coordinate_Bot = (int)randomX_Coordinate;
        return this.x_Coordinate_Bot;
    }

    // Randomise movement of the 'bot within the width of the room
    public int randomiseY_Coordinate() {
        double randomY_Coordinate = Math.random() *
                currentRoom.getWidth()-1;
        this.y_Coordinate_Bot = (int)randomY_Coordinate;
        return this.y_Coordinate_Bot;
    }

    public int getX_Coordinate_Bot() {
        return x_Coordinate_Bot;
    }

    public void setX_Coordinate_Bot(int x_Coordinate_Bot) {
        this.x_Coordinate_Bot = x_Coordinate_Bot;
    }

    public int getY_Coordinate_Bot() {
        return y_Coordinate_Bot;
    }

    public void setY_Coordinate_Bot(int y_Coordinate_Bot) {
        this.y_Coordinate_Bot = y_Coordinate_Bot;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
