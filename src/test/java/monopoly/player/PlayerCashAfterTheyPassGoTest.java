package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.ports.out.EventNotifier;
import monopoly.ports.out.EventNotifierTestDouble;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerCashAfterTheyPassGoTest {
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void playerPassesGoIncreasesTheirCashBy200() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsFour = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FOUR, Die.SIX), new DiceResult(Die.ONE, Die.TWO) , new DiceResult(Die.TWO, Die.TWO)));
        Player player = new Player(DUMMY_NAME, board, diceThatRollsFour, DUMMY_EVENT_NOTIFIER);
        assertEquals(1500, player.cash.value());
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(1, player.position);
        assertEquals(1700, player.cash.value());
    }
}
