package test.monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.tile.Property;
import monopoly.turn.Move;
import monopoly.turn.Option;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

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

        FakeDiceWithResultsQueuedUp diceThatRollsThree = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.TWO, Die.ONE)));
        Player renter = new Player(20, board, diceThatRollsThree);
        renter.makeTurnToPlay();

        // When
        renter.rollDiceAndMove();
        assertTrue(playerCan(Move.PAY_RENT, renter));
        renter.payRentToOwnerOfPropertyAtCurrentPosition();

        // Then
        assertEquals(1482, renter.cash.value());
        assertEquals(1518, owner.cash.value());
        assertFalse(playerCan(Move.PAY_RENT, renter));
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

        FakeDiceWithResultsQueuedUp diceThatRollsThree = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.ONE, Die.TWO)));
        Player renter = new Player(20, board, diceThatRollsThree);
        renter.makeTurnToPlay();

        // When
        renter.rollDiceAndMove();
        renter.payRentToOwnerOfPropertyAtCurrentPosition();

        // Then
        assertEquals(1464, renter.cash.value());
        assertEquals(1536, owner.cash.value());
        assertFalse(playerCan(Move.PAY_RENT, renter));
    }

    private boolean playerCan(Move move, Player player) {
        return player.hasOption(move);
    }
}
