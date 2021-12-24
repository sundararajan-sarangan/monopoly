package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.DiceResult;
import monopoly.dice.Die;
import monopoly.init.StandardBoardMaker;
import monopoly.testdoubles.FakeDiceWithResultsQueuedUp;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerCashAfterTheyPassGoTest {
    @Test
    public void playerPassesGoIncreasesTheirCashBy200() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsFour = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.SIX, Die.SIX), new DiceResult(Die.FOUR, Die.SIX), new DiceResult(Die.ONE, Die.TWO) , new DiceResult(Die.TWO, Die.TWO)));
        Player player = new Player(board, diceThatRollsFour);
        assertEquals(1500, player.cash.value());
        player.makeTurnToPlay();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();
        player.rollDiceAndMove();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(1, player.position);
        assertEquals(1700, player.cash.value());
    }
}
