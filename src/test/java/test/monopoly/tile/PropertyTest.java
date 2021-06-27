package test.monopoly.tile;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.Player;
import monopoly.tile.ColorGroup;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Property;
import monopoly.tile.Rents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyTest {
    private static Property boardWalk;

    @BeforeEach
    public void init() {
        boardWalk = new Property("Boardwalk", 440,
                ColorGroup.DARK_BLUE,
                new Rents(Map.of(DevelopedLevel.NO_HOUSES, 50,
                        DevelopedLevel.ONE_HOUSE, 200,
                        DevelopedLevel.TWO_HOUSES, 600,
                        DevelopedLevel.THREE_HOUSES, 1400,
                        DevelopedLevel.FOUR_HOUSES, 1700,
                        DevelopedLevel.HOTEL, 2000)), DevelopedLevel.NO_HOUSES);
    }

    @Test
    public void testCostOfProperty() {
        assertEquals(440, boardWalk.cost());
    }

    @Test
    public void testNameOfProperty() {
        assertEquals("Boardwalk", boardWalk.name());
    }

    @Test
    public void testColorGroup() {
        assertEquals(ColorGroup.DARK_BLUE, boardWalk.colorGroup());
    }

    @Test void testPropertyOwner() {
        Board board = new Board(40);
        Dice dice = new Dice();
        Player owner = new Player(0, board, dice);
        boardWalk.setOwner(owner);
        assertEquals(owner, boardWalk.owner());
    }
}