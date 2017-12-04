
/**
 * JUnit tests for 'Bot class  for
 * Multi-Player Adventure Game . Testing
 * randomiseX_Coordinate and randomiseY_Coordinate
 *  methods
 *
 * @version 1.0
 * @created 27/11/2017
 * @release 1/12/2017 Addressing  Functional
 *      Requirement FR1b, FR2g, User Story US0017,
 *      SD07
 *
 *  @See Bot.java, PlayerScore.java, Player.java, PlayerTest.java,
 * MainPlayerMoveRoomChange.java
 */

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import static org.junit.Assert.*;

public class BotTest {

    // jUnit test. Test randomised X_Coordinate of 'bot remains
    // within length of room
    @org.junit.Test
    public void randomiseX_Coordinate() throws Exception {
        // establish a failing test initially
        /*
        fail("This test is yet to be implemented.");
        */

        // write code to pass JUnit test
        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Bot robot = new Bot(entrancehall, 3, 3);
        int robotXCoordinate = robot.randomiseX_Coordinate();
        assertTrue(robotXCoordinate < entrancehall.getLength());

    }

    // jUnit test. Test randomised Y_Coordinate of 'bot remains
    // within length of room
    @org.junit.Test
    public void randomiseY_Coordinate() throws Exception {
        // establish a failing test initially
        /*
        fail("This test is yet to be implemented.");
        */

        Room entrancehall =
                new Room("Entrancehall", 8, 10);
        Bot robot = new Bot(entrancehall, 3, 3);
        int robotYCoordinate = robot.randomiseY_Coordinate();
        assertTrue(robotYCoordinate < entrancehall.getWidth());

    }

}