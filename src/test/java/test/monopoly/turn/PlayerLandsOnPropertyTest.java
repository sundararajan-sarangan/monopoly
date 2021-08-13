package test.monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import monopoly.turn.Option;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnPropertyTest {

    @Test
    public void shouldPresentTheOptionToBuyWhenPropertyIsUnowned() {
        // Given
        Board board = makeStandardBoard();
        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.TWO, Die.ONE);
        Player player = new Player(0, board, dice);
        player.makeTurnToPlay();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertTrue(playerCan(Move.BUY, player));
        assertFalse(playerCan(Move.PAY_RENT, player));
    }

    @Test
    public void shouldPresentOnlyTheOptionToPayRentWhenPropertyIsOwnedBySomeoneElse() {
        // Given
        Board board = makeStandardBoard();

        Dice dummyDice = new Dice();
        Player existingOwner = new Player(0, board, dummyDice);
        board.getPropertyAt(3).setOwner(existingOwner);

        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.ONE, Die.TWO);
        Player player = new Player(0, board, dice);
        player.makeTurnToPlay();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertTrue(playerCan(Move.PAY_RENT, player));
        assertFalse(playerCan(Move.BUY, player));
    }

    @Test
    public void shouldPresentNeitherBuyNorRentWhenPlayerAlreadyOwnsProperty() {
        // Given
        Board board = makeStandardBoard();

        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.FIVE, Die.ONE);
        Player player = new Player(0, board, dice);
        player.makeTurnToPlay();
        board.getPropertyAt(6).setOwner(player);

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertFalse(playerCan(Move.BUY, player));
        assertFalse(playerCan(Move.PAY_RENT, player));
    }

    @Test
    public void unOwnablePropertiesShouldntPresentOptionToBuyOrRent() {
        // Given
        Board board = makeStandardBoard();

        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.FOUR, Die.THREE);
        Player player = new Player(10, board, dice);
        player.makeTurnToPlay();

        // When
        assertTrue(player.rollDiceAndMove());

        // Then
        assertEquals(17, player.position);
        assertFalse(playerCan(Move.BUY, player));
        assertFalse(playerCan(Move.PAY_RENT, player));
    }

    @NotNull
    private FakeDiceWithResultsQueuedUp makeFakeDiceWithResultsQueuedUp(Die die1, Die die2) {
        DiceResult diceResult = new DiceResult(die1, die2);
        return new FakeDiceWithResultsQueuedUp(List.of(diceResult));
    }

    private Board makeStandardBoard() {
        return (new StandardBoardMaker()).makeBoard();
    }

    private boolean playerCan(Move move, Player player) {
        for(Option option : player.currentOptions()) {
            if(move.equals(option.move)) {
                return true;
            }
        }

        return false;
    }
}
