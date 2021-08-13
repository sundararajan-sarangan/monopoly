package test.monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerPositionTest {
    @Test
    public void playerStartsAtPosition0() {
        Player player = new Player(0, null, null);
        assertEquals(0, player.position);
    }

    @Test
    public void playerRollsDiceMovesThemForward() {
        Dice dice = new DiceTestStubThatAlwaysRolls(Die.ONE, Die.SIX);
        Board board = new Board(40);
        Player player = new Player(0, board, dice);
        player.rollDiceAndMove();
        assertEquals(7, player.position);
    }

    @Test
    public void playerPositionWrapsAroundTheBoard() {
        Board board = new Board(40);
        Dice dice = new DiceTestStubThatAlwaysRolls(Die.SIX, Die.SIX);
        Player player = new Player(36, board, dice);
        player.rollDiceAndMove();
        assertEquals(8, player.position);
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
