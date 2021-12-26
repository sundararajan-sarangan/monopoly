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

public class PlayerLandsOnTaxTest {
    private static final String DUMMY_NAME = "DUMMY_NAME";
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    @Test
    public void playerLandsOnIncomeTaxLoses200() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsFour = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.ONE, Die.THREE)));
        Player player = new Player(DUMMY_NAME, board, diceThatRollsFour, DUMMY_EVENT_NOTIFIER);
        assertEquals(1500, player.cash.value());
        player.makeTurnToPlay();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(4, player.position);
        assertEquals(1300, player.cash.value());
    }

    @Test
    public void playerLandsOnLuxuryTaxLoses100() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsTo38 = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FIVE, Die.SIX), new DiceResult(Die.ONE, Die.TWO)));
        Player player = new Player(DUMMY_NAME, board, diceThatRollsTo38, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        assertEquals(1500, player.cash.value());

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(38, player.position);
        assertEquals(1400, player.cash.value());
    }
}
