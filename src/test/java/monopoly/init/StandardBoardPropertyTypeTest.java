package monopoly.init;

import monopoly.board.Board;
import monopoly.tile.property.GoToJail;
import monopoly.tile.property.IncomeTax;
import monopoly.tile.property.LuxuryTax;
import monopoly.tile.property.NoneProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardBoardPropertyTypeTest {

    @Test
    public void propertiesOfGroupNoneAreOfTypeNonePropertyExceptGoToJail() {
        Board board = new StandardBoardMaker(new StandardBoardPropertyFactory()).makeBoard();
        assertEquals(NoneProperty.class, board.getPropertyAt(0).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(2).getClass());
        assertEquals(IncomeTax.class, board.getPropertyAt(4).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(2).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(7).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(10).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(17).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(20).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(22).getClass());
        assertEquals(GoToJail.class, board.getPropertyAt(30).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(33).getClass());
        assertEquals(NoneProperty.class, board.getPropertyAt(36).getClass());
        assertEquals(LuxuryTax.class, board.getPropertyAt(38).getClass());
    }
}
