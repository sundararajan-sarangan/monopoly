package monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;
import monopoly.testdoubles.EventNotifierTestDouble;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnPropertyTest {
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void shouldPresentTheOptionToBuyWhenPropertyIsUnowned() {
        // Given
        Board board = makeStandardBoard();
        DiceResult diceResult = new DiceResult(Die.TWO, Die.ONE);
        FakeDiceWithResultsQueuedUp dice = new FakeDiceWithResultsQueuedUp(List.of(diceResult));
        Player player = new Player(DUMMY_NAME, board, dice, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertTrue(player.hasOptionTo(Move.BUY));
        assertFalse(player.hasOptionTo(Move.PAY_RENT));
    }

    @Test
    public void shouldPresentOnlyTheOptionToPayRentWhenPropertyIsOwnedBySomeoneElse() {
        // Given
        Board board = makeStandardBoard();

        Dice dummyDice = new Dice();
        Player existingOwner = new Player(DUMMY_NAME, board, dummyDice, DUMMY_EVENT_NOTIFIER);
        board.getPropertyAt(3).setOwner(existingOwner);

        DiceResult diceResult = new DiceResult(Die.ONE, Die.TWO);
        FakeDiceWithResultsQueuedUp dice = new FakeDiceWithResultsQueuedUp(List.of(diceResult));
        Player player = new Player(DUMMY_NAME, board, dice, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertTrue(player.hasOptionTo(Move.PAY_RENT));
        assertFalse(player.hasOptionTo(Move.BUY));
    }

    @Test
    public void shouldPresentNeitherBuyNorRentWhenPlayerAlreadyOwnsProperty() {
        // Given
        Board board = makeStandardBoard();

        DiceResult diceResult = new DiceResult(Die.FIVE, Die.ONE);
        FakeDiceWithResultsQueuedUp dice = new FakeDiceWithResultsQueuedUp(List.of(diceResult));
        Player player = new Player(DUMMY_NAME, board, dice, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();
        board.getPropertyAt(6).setOwner(player);

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertFalse(player.hasOptionTo(Move.BUY));
        assertFalse(player.hasOptionTo(Move.PAY_RENT));
    }

    @Test
    public void unOwnablePropertiesShouldntPresentOptionToBuyOrRent() {
        // Given
        Board board = makeStandardBoard();

        FakeDiceWithResultsQueuedUp dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.FOUR), new DiceResult(Die.FOUR, Die.THREE)));
        Player player = new Player(DUMMY_NAME, board, dice, DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();
        player.rollDiceAndMove();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertEquals(17, player.position);
        assertFalse(player.hasOptionTo(Move.BUY));
        assertFalse(player.hasOptionTo(Move.PAY_RENT));
    }

    private Board makeStandardBoard() {
        return (new StandardBoardMaker()).makeBoard();
    }

}
