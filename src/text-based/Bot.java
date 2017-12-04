public class Bot {

    private int x_Coordinate_Bot;
    private int y_Coordinate_Bot;
    Room currentRoom;



    public Bot(Room currentRoom, int x_Coordinate_Bot, int y_Coordinate_Bot) {
        this.x_Coordinate_Bot = x_Coordinate_Bot;
        this.y_Coordinate_Bot = y_Coordinate_Bot;
        this.currentRoom = currentRoom;
    }

    public int randomiseX_Coordinate() {
        double randomX_Coordinate = Math.random() * currentRoom.getLength()-1;
        this.x_Coordinate_Bot = (int)randomX_Coordinate;
        return this.x_Coordinate_Bot;
    }

    public int randomiseY_Coordinate() {
        double randomY_Coordinate = Math.random() * currentRoom.getWidth()-1;
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
