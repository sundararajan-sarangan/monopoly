package monopoly.turn;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.dice.Dice;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;
import monopoly.testdoubles.EventNotifierTestDouble;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerEndsTurnTest {
    private static final EventNotifier DUMMY_EVENT_NOTIFIER = new EventNotifierTestDouble(new ArrayList<>());
    private static final String DUMMY_NAME = "DUMMY_NAME";
    @Test
    public void playerEndsTurnTest() {
        // Given
        Player player = new Player(DUMMY_NAME, new StandardBoardMaker().makeBoard(), new Dice(), DUMMY_EVENT_NOTIFIER);
        player.makeTurnToPlay();
        assertTrue(player.hasOptionTo(Move.TURN_TO_PLAY));

        // When
        player.endTurn();

        // Then
        assertFalse(player.hasOptionTo(Move.TURN_TO_PLAY));
        assertTrue(player.hasOptionTo(Move.CANNOT_PLAY));
    }
}
