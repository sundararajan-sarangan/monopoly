package monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;
import monopoly.ports.out.EventNotifierTestDouble;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnUnOwnedPropertyTest {
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void playerLandsOnUnOwnedPropertyAndBuysIt() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();

        FakeDiceWithResultsQueuedUp diceThatRollsEleven = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FIVE, Die.FOUR), new DiceResult(Die.SIX, Die.FIVE)));
        Player player = new Player(DUMMY_NAME, board, diceThatRollsEleven, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        assertTrue(player.canBuy());

        // When
        assertTrue(player.buyCurrentProperty());

        // Then
        assertEquals(1200, player.cash.value());
        assertFalse(player.canBuy());
        assertEquals(player, board.getPropertyAt(32).owner());
    }
}
