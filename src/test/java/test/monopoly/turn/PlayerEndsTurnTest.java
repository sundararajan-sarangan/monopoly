package test.monopoly.turn;

import monopoly.dice.Dice;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerEndsTurnTest {
    @Test
    public void playerEndsTurnTest() {
        // Given
        Player player = new Player(new StandardBoardMaker().makeBoard(), new Dice());
        player.makeTurnToPlay();
        assertTrue(player.hasOptionTo(Move.TURN_TO_PLAY));

        // When
        player.endTurn();

        // Then
        assertFalse(player.hasOptionTo(Move.TURN_TO_PLAY));
        assertEquals(0, player.currentOptions().size());
    }
}
