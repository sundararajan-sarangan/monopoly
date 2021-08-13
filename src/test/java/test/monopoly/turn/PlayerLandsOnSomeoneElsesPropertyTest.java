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
        Player owner = new Player(0, board, dummyDice);
        Property indianaAvenue = board.getPropertyAt(23);
        indianaAvenue.setOwner(owner);

        FakeDiceWithResultsQueuedUp diceThatRollsThree = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.TWO, Die.ONE)));
        Player renter = new Player(20, board, diceThatRollsThree);
        renter.makeTurnToPlay();

        // When
        renter.rollDiceAndMove();

        // Then
        assertTrue(playerCan(Move.PAY_RENT, renter));
        renter.payRentToOwnerOfPropertyAtCurrentPosition();
        assertEquals(1500 - indianaAvenue.rent(false), renter.cash.value());
        assertEquals(1500 + indianaAvenue.rent(false), owner.cash.value());
        assertFalse(playerCan(Move.PAY_RENT, renter));
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
