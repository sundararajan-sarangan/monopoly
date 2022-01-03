package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.ports.out.EventNotifier;
import monopoly.testdoubles.EventNotifierTestDouble;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerInTheRedOptionsTest {
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void playerCanGoBustIfInTheNegative() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.FIVE, Die.FIVE), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.ONE, Die.ONE)));
        Player player = new Player(DUMMY_NAME, board, dice, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();

        // When
        player.takeAway(player.cash.value() + 1);

        // Then
        assertTrue(player.currentOptions().contains(Move.GO_BUST));
    }
}
