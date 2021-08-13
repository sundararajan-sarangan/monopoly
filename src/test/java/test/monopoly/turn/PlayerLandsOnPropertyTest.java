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

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(Move.BUY, player.currentOptions().get(0).move);
    }

    @Test
    public void shouldPresentOnlyTheOptionToPayRentWhenPropertyIsOwnedBySomeoneElse() {
        // Given
        Board board = makeStandardBoard();

        Dice dummyDice = new Dice();
        Player existingOwner = new Player(0, board, dummyDice);
        board.getPropertyAt(3).setOwner(existingOwner);

        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.TWO, Die.ONE);
        Player player = new Player(0, board, dice);

        // When
        player.rollDiceAndMove();

        // Then
        List<Option> currentOptions = player.currentOptions();
        Option zerothOption = currentOptions.get(0);
        assertEquals(Move.PAY_RENT, zerothOption.move);
        for(Option option : currentOptions) {
            assertNotEquals(Move.BUY, option.move);
        }
    }

    @Test
    public void shouldPresentNeitherBuyNorRentWhenPlayerAlreadyOwnsProperty() {
        // Given
        Board board = makeStandardBoard();

        FakeDiceWithResultsQueuedUp dice = makeFakeDiceWithResultsQueuedUp(Die.FIVE, Die.ONE);
        Player player = new Player(0, board, dice);
        board.getPropertyAt(6).setOwner(player);

        // When
        player.rollDiceAndMove();

        // Then
        List<Option> currentOptions = player.currentOptions();
        for(Option option : currentOptions) {
            assertNotEquals(Move.BUY, option.move);
            assertNotEquals(Move.PAY_RENT, option.move);
        }
    }

    @NotNull
    private FakeDiceWithResultsQueuedUp makeFakeDiceWithResultsQueuedUp(Die die1, Die die2) {
        DiceResult diceResult = new DiceResult(die1, die2);
        return new FakeDiceWithResultsQueuedUp(List.of(diceResult));
    }

    private Board makeStandardBoard() {
        return (new StandardBoardMaker()).makeBoard();
    }
}
