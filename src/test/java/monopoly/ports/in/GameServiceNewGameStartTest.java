package monopoly.ports.in;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.dice.Dice;
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
        GameService gameService = new StandardGameService(new EventNotifierTestDouble(new ArrayList<>()));
        gameService.addPlayer("Zip");
        gameService.addPlayer("Zop");
        gameService.addPlayer("Zap");

        // When & Then
        gameService.startGame();
    }

    @Test
    public void startingANewGameNotifiesFirstPlayerItsTheirTurnToPlay() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        GameService gameService = new StandardGameService(eventNotifier);
        gameService.addPlayer("bleep");
        gameService.addPlayer("bloop");

        // When
        gameService.startGame();

        // Then
        assertEquals("bleep", playerEvents.get(0).name);
        assertEquals(Move.TURN_TO_PLAY, playerEvents.get(0).move);
        assertEquals("bloop", playerEvents.get(1).name);
        assertEquals(Move.CANNOT_PLAY, playerEvents.get(1).move);
    }

    @Test
    public void startingGameWithDuplicatePlayersThrowsException() {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        GameService gameService = new StandardGameService(eventNotifier);
        gameService.addPlayer("Goo");
        gameService.addPlayer("Gaa");
        gameService.addPlayer("Goo");

        // When && Then
        Exception thrownException = assertThrows(Exception.class, gameService::startGame);
        assertTrue(thrownException.getMessage().contains("Two players cannot have the same name"));
    }

    @Test
    public void firstPlayerCanRollDiceAfterNewGameIsStarted() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        GameService gameService = new StandardGameService(eventNotifier);
        gameService.addPlayer("Player1");
        gameService.addPlayer("Player2");
        gameService.addPlayer("Player3");
        gameService.startGame();

        // When && Then
        assertTrue(gameService.rollDiceFor("Player1"));
    }

    @Test
    public void nonFirstPlayerCannotRollDiceAfterNewGameIsStarted() throws Exception {
        // Given
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        GameService gameService = new StandardGameService(eventNotifier);
        gameService.addPlayer("Player1");
        gameService.addPlayer("Player2");
        gameService.addPlayer("Player3");
        gameService.startGame();

        // When && Then
        assertFalse(gameService.rollDiceFor("Player2"));
    }
}
