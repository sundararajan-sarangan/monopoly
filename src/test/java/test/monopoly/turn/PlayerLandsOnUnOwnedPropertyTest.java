package test.monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnUnOwnedPropertyTest {

    @Test
    public void playerLandsOnUnOwnedPropertyAndBuysIt() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();

        FakeDiceWithResultsQueuedUp diceThatRollsEleven = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FIVE, Die.FOUR), new DiceResult(Die.SIX, Die.FIVE)));
        Player player = new Player(board, diceThatRollsEleven);
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
