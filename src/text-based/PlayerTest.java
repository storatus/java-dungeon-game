
/**
 * JUnit tests for Player class  for
 * Multi-Player Adventure Game . Testing
 * Player.java moveRight(),  moveLeft()
 * moveUp() and moveDown() methods
 *
 * @version 1.0
 * @gitCommit 1.5
 * @release 21/11/2017 Addressing  Functional
 *      Requirement FR1a, FR2f, User Story US005,
 *      US006, US007
 *
 *  @See PlayerScore.java, Player.java, PlayerTest.java,
 * MainPlayerMoveRoomChange.java
 */


import org.junit.internal.runners.statements.Fail;

import static org.junit.Assert.*;

public class PlayerTest {

    // test player rightwards movement
    @org.junit.Test
    public void moveRight() throws Exception {
        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Player bob = new Player(entrancehall,
                5, 5);
        int rightLocation = bob.moveRight();
        assertEquals(6, rightLocation, 0);

        // test player does not move beyond wall boundary
        Player bc =
                new Player(entrancehall,
                        7, 9);
        int rightLocation2 = bc.moveRight();
        assertEquals(9, rightLocation2, 0);
    }

    // test player left movement
    @org.junit.Test
    public void moveLeft() throws Exception {
        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Player bob = new Player(entrancehall,
                5, 5);
        int leftLocation = bob.moveLeft();
        assertEquals(4, leftLocation, 0);

        // test player does not move beyond wall boundary
        Player bc =
                new Player(entrancehall, 0, 0);
        int leftLocation2 = bc.moveLeft();
        assertEquals(0, leftLocation2, 0);

    }

    //test player upward movement
    @org.junit.Test
    public void moveDown() throws Exception {
        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Player bob = new Player(entrancehall,
                5, 5);
        int downLocation = bob.moveDown();
        assertEquals(6, downLocation, 0);

        // test player does not move beyond wall boundary
        Player bc =
                new Player(entrancehall,
                        7, 9);
        int downLocation2 = bc.moveDown();
        assertEquals(7, downLocation2, 0);

    }

    // test player upward movement
    @org.junit.Test
    public void moveUp() throws Exception {
        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Player bob = new Player(entrancehall,
                5, 5);
        int upLocation = bob.moveUp();
        assertEquals(4, upLocation, 0);

        // test player does not move beyond wall boundary
        Player bc =
                new Player(entrancehall,
                        0, 0);
        int upLocation2 = bc.moveUp();
        assertEquals(0, upLocation2, 0);
    }

}
