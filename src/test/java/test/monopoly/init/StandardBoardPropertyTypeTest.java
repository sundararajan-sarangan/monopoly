package test.monopoly.init;

import monopoly.board.Board;
import monopoly.init.GoToJailProperty;
import monopoly.init.NoneProperty;
import monopoly.init.StandardBoardMaker;
import monopoly.init.StandardBoardPropertyFactory;
import monopoly.tile.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardBoardPropertyTypeTest {

    @Test
    public void propertiesOfGroupNoneAreOfTypeNonePropertyExceptGoToJail() {
        Board board = new StandardBoardMaker(new StandardBoardPropertyFactory()).makeBoard();
        assertEquals(NoneProperty.class, board.getPropertyAt(0).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(2).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(4).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(2).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(7).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(10).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(17).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(20).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(22).getClass());
        assertEquals(GoToJailProperty.class, board.getPropertyAt(30).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(33).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(36).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(38).getClass());
    }
}
