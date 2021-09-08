package test.monopoly.player;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import org.junit.jupiter.api.Test;
import test.monopoly.testdoubles.FakeDiceWithResultsQueuedUp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerLandsOnTaxTest {

    @Test
    public void playerLandsOnIncomeTaxLoses200() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsFour = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.ONE, Die.THREE)));
        Player player = new Player(board, diceThatRollsFour);
        assertEquals(1500, player.cash.value());
        player.makeTurnToPlay();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(4, player.position);
        assertEquals(1300, player.cash.value());
    }

    @Test
    public void playerLandsOnLuxuryTaxLoses100() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsTo38 = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FIVE, Die.SIX), new DiceResult(Die.ONE, Die.TWO)));
        Player player = new Player(board, diceThatRollsTo38);
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        assertEquals(1500, player.cash.value());

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(38, player.position);
        assertEquals(1400, player.cash.value());
    }
}
