package test.monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerInTheRedOptionsTest {
    @Test
    public void playerCanGoBustIfInTheNegative() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.FIVE, Die.FIVE), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.ONE, Die.ONE)));
        Player player = new Player(board, dice);
        player.makeTurnToPlay();

        // When
        player.takeAway(player.cash.value() + 1);

        // Then
        assertTrue(player.currentOptions().contains(Move.GO_BUST));
    }
}
