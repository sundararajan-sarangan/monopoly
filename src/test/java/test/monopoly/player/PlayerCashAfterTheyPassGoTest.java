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

public class PlayerCashAfterTheyPassGoTest {
    @Test
    public void playerPassesGoIncreasesTheirCashBy200() {
        // Given
        Board board = new StandardBoardMaker().makeBoard();
        FakeDiceWithResultsQueuedUp diceThatRollsFour = new FakeDiceWithResultsQueuedUp(List.of(new DiceResult(Die.TWO, Die.TWO)));
        Player player = new Player(37, board, diceThatRollsFour);
        assertEquals(1500, player.cash.value());
        player.makeTurnToPlay();

        // When
        player.rollDiceAndMove();

        // Then
        assertEquals(1, player.position);
        assertEquals(1700, player.cash.value());
    }
}
