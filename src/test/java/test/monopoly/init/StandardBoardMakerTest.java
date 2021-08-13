package test.monopoly.init;

import monopoly.board.Board;
import monopoly.init.StandardBoardMaker;
import monopoly.tile.Group;
import monopoly.tile.Property;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardBoardMakerTest {

    @Test
    public void zeroethTileIsGoTile() throws FileNotFoundException {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(0);
        assertEquals(Group.NONE, property.group());
        assertEquals("Go", property.name());
        assertEquals(0, property.rent(false));
        assertEquals(0, property.cost());
    }

    @Test
    public void firstTileIsMediterranean() throws FileNotFoundException {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(1);
        assertEquals(Group.BROWN, property.group());
        assertEquals("Mediterranean Avenue", property.name());
        assertEquals(2, property.rent(false));
    }

    @Test
    public void fourthTileIsIncomeTax() throws FileNotFoundException {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(4);
        assertEquals(Group.NONE, property.group());
        assertEquals("Income Tax", property.name());
        assertEquals(200, property.rent(false));
        assertEquals(200, property.cost());
    }
}
