package test.monopoly.turn;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import monopoly.turn.Option;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerLandsOnUnOwnedPropertyTest {

    @Test
    public void playerLandsOnUnOwnedPropertyAndBuysIt() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();

        FakeDiceWithResultsQueuedUp diceThatRollsEleven = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.FIVE)));
        Player player = new Player(21, board, diceThatRollsEleven);
        player.makeTurnToPlay();

        player.rollDiceAndMove();
        assertTrue(playerCan(Move.BUY, player));

        // When
        assertTrue(player.buyCurrentProperty());

        // Then
        assertEquals(1200, player.cash.value());
        assertFalse(playerCan(Move.BUY, player));
        assertEquals(player, board.getPropertyAt(32).owner());
    }

    private boolean playerCan(Move move, Player player) {
        return player.hasOption(move);
    }
}
