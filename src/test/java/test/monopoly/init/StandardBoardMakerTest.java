package test.monopoly.init;

import monopoly.board.Board;
import monopoly.init.GoToJailProperty;
import monopoly.init.StandardBoardMaker;
import monopoly.init.StandardBoardPropertyFactory;
import monopoly.tile.Group;
import monopoly.tile.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StandardBoardMakerTest {

    @Test
    public void zeroethTileIsGoTile() {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(0);
        assertEquals(Group.NONE, property.group());
        assertEquals("Go", property.name());
        assertEquals(0, property.cost());
    }

    @Test
    public void firstTileIsMediterranean() {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(1);
        assertEquals(Group.BROWN, property.group());
        assertEquals("Mediterranean Avenue", property.name());
    }

    @Test
    public void fourthTileIsIncomeTax() {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(4);
        assertEquals(Group.NONE, property.group());
        assertEquals("Income Tax", property.name());
        assertEquals(0, property.cost());
    }

    @Test
    public void thirtyEighthTileIsLuxuryTax() {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(38);
        assertEquals(Group.NONE, property.group());
        assertEquals("Luxury Tax", property.name());
    }

    @Test
    public void thirtySixthTileIsChance() {
        Board board = new StandardBoardMaker().makeBoard();
        Property property = board.getPropertyAt(36);
        assertEquals(Group.NONE, property.group());
        assertEquals("Chance", property.name());
    }

    @Test
    public void GoToJailPropertyIsOfTypeGoToJail() {
        Board board = new StandardBoardMaker(new StandardBoardPropertyFactory()).makeBoard();
        Property property = board.getPropertyAt(30);
        assertEquals("Go To Jail", property.name());
        assertEquals(property.getClass(), GoToJailProperty.class);
    }
}
