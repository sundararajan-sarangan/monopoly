package monopoly.ports.in;

import monopoly.ports.out.EventNotifier;
import monopoly.testdoubles.EventNotifierTestDouble;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceNewGameStartTest {

    @Test
    public void startGameWithSomePlayersTest() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("Zip");
        singleGameService.addPlayer("Zop");
        singleGameService.addPlayer("Zap");

        // When & Then
        singleGameService.startGame();
    }

    @Test
    public void startingANewGameNotifiesFirstPlayerItsTheirTurnToPlay() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(eventNotifier);
        singleGameService.addPlayer("bleep");
        singleGameService.addPlayer("bloop");

        // When
        singleGameService.startGame();

        // Then
        assertEquals("bleep", playerEvents.get(0).name);
        assertEquals(Move.TURN_TO_PLAY, playerEvents.get(0).move);
        assertEquals("bloop", playerEvents.get(1).name);
        assertEquals(Move.CANNOT_PLAY, playerEvents.get(1).move);
    }

    @Test
    public void startingGameWithDuplicatePlayersThrowsException() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(eventNotifier);
        singleGameService.addPlayer("Goo");
        singleGameService.addPlayer("Gaa");
        singleGameService.addPlayer("Goo");

        // When && Then
        Exception thrownException = assertThrows(Exception.class, singleGameService::startGame);
        assertTrue(thrownException.getMessage().contains("Two players cannot have the same name"));
    }

    @Test
    public void firstPlayerCanRollDiceAfterNewGameIsStarted() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(eventNotifier);
        singleGameService.addPlayer("Player1");
        singleGameService.addPlayer("Player2");
        singleGameService.addPlayer("Player3");
        singleGameService.startGame();

        // When && Then
        assertTrue(singleGameService.rollDiceFor("Player1"));
    }

    @Test
    public void nonFirstPlayerCannotRollDiceAfterNewGameIsStarted() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(eventNotifier);
        singleGameService.addPlayer("Player1");
        singleGameService.addPlayer("Player2");
        singleGameService.addPlayer("Player3");
        singleGameService.startGame();

        // When && Then
        assertFalse(singleGameService.rollDiceFor("Player2"));
    }

    @Test
    public void cannotAddPlayersAfterGameHasAlreadyStarted() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("Player1");
        singleGameService.addPlayer("Player2");
        singleGameService.startGame();

        // When && Then
        Exception thrown = assertThrows(Exception.class, () -> singleGameService.addPlayer("Player3"));
        assertEquals("Game already in progress. Cannot add a player!", thrown.getMessage());
    }
}
