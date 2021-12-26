package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerPositionTest {
    @Test
    public void playerStartsAtPosition0() {
        Player player = new Player(null, null);
        assertEquals(0, player.position);
    }

    @Test
    public void playerRollsDiceMovesThemForward() {
        Dice dice = new DiceTestStubThatAlwaysRolls(Die.ONE, Die.SIX);
        Board board = new StandardBoardMaker().makeBoard();
        Player player = new Player(board, dice);
        player.makeTurnToPlay();
        assertTrue(player.rollDiceAndMove());
        assertEquals(7, player.position);
    }

    @Test
    public void playerPositionWrapsAroundTheBoard() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FIVE, Die.FOUR), new DiceResult(Die.TWO, Die.ONE), new DiceResult(Die.SIX, Die.SIX)));
        Player player = new Player(board, dice);
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();

        // When and Then
        assertTrue(player.rollDiceAndMove());
        assertEquals(8, player.position);
    }

    @Test
    public void playerCannotRollDiceIfItsNotTheirTurn() {
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.FIVE, Die.FOUR)));
        Player player = new Player(board, dice);
        assertFalse(player.rollDiceAndMove());
        assertEquals(0, player.position);
    }

    private static class DiceTestStubThatAlwaysRolls extends Dice {
        private final Die die1;
        private final Die die2;

        public DiceTestStubThatAlwaysRolls(Die die1, Die die2) {
            this.die1 = die1;
            this.die2 = die2;
        }

        @Override
        public DiceResult roll() {
            return new DiceResult(die1, die2);
        }
    }
}