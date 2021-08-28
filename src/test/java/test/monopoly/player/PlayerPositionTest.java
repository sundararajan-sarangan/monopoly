package test.monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

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
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new DiceTestStubThatAlwaysRolls(Die.SIX, Die.SIX);
        Player player = new Player(36, board, dice);
        player.makeTurnToPlay();
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
