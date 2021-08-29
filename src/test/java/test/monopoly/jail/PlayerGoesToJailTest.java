package test.monopoly.jail;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerGoesToJailTest {
    @Test
    public void playerGoesToJailIfTheyLandOnGoToJailTile() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.FIVE, Die.FIVE), new DiceResult(Die.SIX, Die.FOUR), new DiceResult(Die.FOUR, Die.SIX)));
        Player player = new Player(board, dice);
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(10, player.position);
        assertFalse(player.currentOptions().contains(Move.BUY));
        assertFalse(player.currentOptions().contains(Move.PAY_RENT));
    }

    @Test
    public void playerGoesToJailIfTheyRollThreeConsecutiveDoubles() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        Dice dice = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.FIVE, Die.FIVE), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.ONE, Die.ONE)));
        Player player = new Player(board, dice);
        player.makeTurnToPlay();

        // When
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();

        // Then
        assertEquals(10, player.position);
    }
}
