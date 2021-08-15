package test.monopoly.tile;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.tile.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyUpgradesTest {
    private static Property boardWalk;
    private static Board board;
    private static Player owner;

    @BeforeEach
    public void init() {
        board = new StandardBoardMaker().makeBoard();
        boardWalk = board.getPropertyAt(39);
        owner = new Player(0, board, new Dice());
        boardWalk.setOwner(owner);
    }

    @Test
    public void testPropertyRentWithNoHouses() {
        assertEquals(50, boardWalk.rent(board));
    }

    @Test
    public void testPropertyRentWithOneHouse() {
        boardWalk.upgrade();
        assertEquals(200, boardWalk.rent(board));
    }

    @Test
    public void testPropertyRentWithTwoHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(600, boardWalk.rent(board));
    }

    @Test
    public void testPropertyRentWithThreeHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1400, boardWalk.rent(board));
    }

    @Test
    public void testPropertyRentWithFourHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1700, boardWalk.rent(board));
    }

    @Test
    public void testPropertyRentWithHotel() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(2000, boardWalk.rent(board));
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
