package test.monopoly.board;

import monopoly.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardPositionsTest {
    @Test
    public void canCreateNewBoardWithSomeSizeGreaterThan11() {
        Board board = new Board(40);
    }

    @Test
    public void numberOfTilesLessThanTwelveThrowsException() {
        assertThrows(RuntimeException.class, () -> new Board(-1));
        assertThrows(RuntimeException.class, () -> new Board(0));
        assertThrows(RuntimeException.class, () -> new Board(11));
    }

    @Test
    public void getNewPositionGivenConditionsThatDontCauseWrapAround() {
        Board board = new Board(40);
        assertEquals(7, board.getNewPosition(0, 7));
        assertEquals(9, board.getNewPosition(3, 6));
    }

    @Test
    public void getNewPositionGivenConditionsThatCauseWrapAround() {
        Board board = new Board(40);
        assertEquals(0, board.getNewPosition(39, 1));
        assertEquals(1, board.getNewPosition(39, 2));
        assertEquals(0, board.getNewPosition(0, 0));
        assertEquals(0, board.getNewPosition(0, 40));
    }
}
