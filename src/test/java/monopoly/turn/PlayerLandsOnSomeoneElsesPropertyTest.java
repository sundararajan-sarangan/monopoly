package monopoly.turn;

import monopoly.api.Property;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnSomeoneElsesPropertyTest {
    @Test
    public void playerPaysRentIfTheyLandOnPropertyOwnedBySomeoneElse() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dummyDice = new Dice();
        Player owner = new Player(board, dummyDice);
        Property indianaAvenue = board.getPropertyAt(23);
        indianaAvenue.setOwner(owner);

        Player renter = getPlayerAtPosition20AboutToRoll3(board);

        // When
        renter.rollDiceAndMove();
        assertTrue(renter.canPayRent());
        renter.payRentToOwnerOfPropertyAtCurrentPosition();

        // Then
        assertEquals(1482, renter.cash.value());
        assertEquals(1518, owner.cash.value());
        assertFalse(renter.canPayRent());
    }

    @Test
    public void playerPaysDoubleRentIfOwnerOwnsAllPropertiesOfTheSameColorGroup() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dummyDice = new Dice();
        Player owner = new Player(board, dummyDice);
        Property kentuckyAvenue = board.getPropertyAt(21);
        kentuckyAvenue.setOwner(owner);
        Property indianaAvenue = board.getPropertyAt(23);
        indianaAvenue.setOwner(owner);
        Property illinoisAvenue = board.getPropertyAt(24);
        illinoisAvenue.setOwner(owner);

        Player renter = getPlayerAtPosition20AboutToRoll3(board);

        // When
        renter.rollDiceAndMove();
        renter.payRentToOwnerOfPropertyAtCurrentPosition();

        // Then
        assertEquals(1464, renter.cash.value());
        assertEquals(1536, owner.cash.value());
        assertFalse(renter.canPayRent());
    }

    @NotNull
    private Player getPlayerAtPosition20AboutToRoll3(Board board) {
        FakeDiceWithResultsQueuedUp dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.THREE, Die.FIVE), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.ONE, Die.TWO)));
        Player renter = new Player(board, dice);
        renter.makeTurnToPlay();
        renter.rollDiceAndMove();
        renter.rollDiceAndMove();
        assertEquals(20, renter.position);
        return renter;
    }
}
