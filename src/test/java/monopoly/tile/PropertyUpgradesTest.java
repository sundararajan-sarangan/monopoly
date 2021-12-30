package monopoly.tile;

import monopoly.api.Property;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;
import monopoly.testdoubles.EventNotifierTestDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyUpgradesTest {
    private static Property boardWalk;
    private static Board board;
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @BeforeEach
    public void init() {
        board = new StandardBoardMaker().makeBoard();
        boardWalk = board.getPropertyAt(39);
        Player owner = new Player(DUMMY_NAME, board, new Dice(), DUMMY_EVENT_NOTIFIER);
        boardWalk.setOwner(owner);
    }

    @Test
    public void testPropertyRentWithNoHouses() {
        assertEquals(50, board.rentFor(boardWalk));
    }

    @Test
    public void testPropertyRentWithOneHouse() {
        boardWalk.upgrade();
        assertEquals(200, board.rentFor(boardWalk));
    }

    @Test
    public void testPropertyRentWithTwoHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(600, board.rentFor(boardWalk));
    }

    @Test
    public void testPropertyRentWithThreeHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1400, board.rentFor(boardWalk));
    }

    @Test
    public void testPropertyRentWithFourHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1700, board.rentFor(boardWalk));
    }

    @Test
    public void testPropertyRentWithHotel() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(2000, board.rentFor(boardWalk));
    }

    @Test
    public void testUpgradingMoreThanAllowed() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertThrows(RuntimeException.class, () -> boardWalk.upgrade());
    }
}
