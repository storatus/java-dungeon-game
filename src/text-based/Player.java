

/**
 * Class. Human Player for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 1.0
 * @created 15/11/2017
 * @release 19/11/2017 Addressing  Functional
 *      Requirement FR1a, FR2f, User Story US005,
 *      US006, US007
 * @See PlayerScore.java, Player.java, PlayerTest.java,
 * MainPlayerMoveRoomChange.java
 */

public class Player {
    private int x_Coordinate_Player;
    private int y_Coordinate_Player;
    private int goldScore;
    Coordinates playerCoordinates;
    Room currentRoom;

    public Player(Room currentRoom,
                      int x_Coordinate_Player, int y_Coordinate_Player) {
        this.currentRoom = currentRoom;
        this.x_Coordinate_Player = x_Coordinate_Player;
        this.y_Coordinate_Player = y_Coordinate_Player;
        this.goldScore = 200;
    }

    public int moveRight() {
        if(this.y_Coordinate_Player == currentRoom.getWidth()-1) {
            System.out.println("You hit the wall");
            setY_Coordinate_Player(currentRoom.getWidth()-1);
        } else {
            this.y_Coordinate_Player++;
        }

        return this.y_Coordinate_Player;
    }

    public int moveLeft() {
        if(this.y_Coordinate_Player == 0) {
            System.out.println("You hit the wall");
            setY_Coordinate_Player(0);
        } else {
            this.y_Coordinate_Player--;
        }

        return this.y_Coordinate_Player;
    }

    public int moveDown() {
        if(this.x_Coordinate_Player == currentRoom.getLength()-1) {
            System.out.println("You hit the wall");
            setX_Coordinate_Player(currentRoom.getLength()-1);
        } else {
            this.x_Coordinate_Player++;;
        }

        return this.x_Coordinate_Player;
    }

    public int moveUp() {
        if(this.x_Coordinate_Player == 0) {
            System.out.println("You hit the wall");
            setX_Coordinate_Player(0);
        } else {
            this.x_Coordinate_Player--;
        }

        return this.x_Coordinate_Player;
    }



    public int getX_Coordinate_Player() {
        return x_Coordinate_Player;
    }

    public void setX_Coordinate_Player(int x_Coordinate_Player) {

        this.x_Coordinate_Player = x_Coordinate_Player;
    }

    public int getY_Coordinate_Player() {
        return y_Coordinate_Player;
    }

    public void setY_Coordinate_Player(int y_Coordinate_Player) {

        this.y_Coordinate_Player = y_Coordinate_Player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getGoldScore() {
        return goldScore;
    }

    public void setGoldScore(int goldScore) {
        this.goldScore = goldScore;
    }

}
