package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.ports.out.EventNotifier;
import monopoly.ports.out.EventNotifierTestDouble;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerCashTest {
    private final static Board DUMMY_BOARD = new Board(null);
    private final static EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private final static String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void playerHas1500CashByDefault() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        assertEquals(1500, player.cash.value());
    }

    @Test
    public void playerIsGiven200() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.give(200);
        assertEquals(1700, player.cash.value());
    }

    @Test
    public void playerPaysBack300() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.takeAway(300);
        assertEquals(1200, player.cash.value());
    }

    @Test
    public void playerIsGivenAndThenTakesBack() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.give(500);
        player.takeAway(400);
        assertEquals(1600, player.cash.value());
    }

    @Test
    public void playerInTheRed() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.takeAway(1700);
        assertTrue(player.inTheRed());
    }

    @Test
    public void brokePlayerNotConsideredToBeInTheRed() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.takeAway(player.cash.value());
        assertFalse(player.inTheRed());
        assertEquals(0, player.cash.value());
    }

    @Test
    public void playerWithPositiveCashBalanceNotConsideredToBeInTheRed() {
        Player player = new Player(DUMMY_NAME, DUMMY_BOARD, new Dice(), DUMMY_EVENT_NOTIFIER);
        player.give(500);
        assertFalse(player.inTheRed());
        assertEquals(2000, player.cash.value());
    }
}
