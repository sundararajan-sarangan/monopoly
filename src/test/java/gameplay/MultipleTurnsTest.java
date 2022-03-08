package gameplay;

import monopoly.ports.in.SingleGameService;
import monopoly.ports.in.StandardSingleGameService;
import monopoly.testdoubles.EventNotifierTestDouble;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleTurnsTest {
    @Test
    public void firstPlayerCompletesTurnAllowsNextPlayerToPlayTest() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.startGame();
        singleGameService.rollDiceFor("player1");

        // When
        singleGameService.endTurnFor("player1");

        // Then
        assertTrue(singleGameService.rollDiceFor("player2"));
    }

    @Test
    public void twoPlayersCompleteTurnAllowsThirdPlayerToPlayTest() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.addPlayer("player3");
        singleGameService.startGame();
        singleGameService.rollDiceFor("player1");
        singleGameService.endTurnFor("player1");
        singleGameService.rollDiceFor("player2");

        // When
        singleGameService.endTurnFor("player2");

        // Then
        assertTrue(singleGameService.rollDiceFor("player3"));
    }

    @Test
    public void shouldReturnToFirstPlayerWhenAllPlayersHaveGoneOneRound() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.addPlayer("player3");
        singleGameService.startGame();
        singleGameService.rollDiceFor("player1");
        singleGameService.endTurnFor("player1");
        singleGameService.rollDiceFor("player2");
        singleGameService.endTurnFor("player2");
        singleGameService.rollDiceFor("player3");

        // When
        singleGameService.endTurnFor("player3");

        // Then
        assertTrue(singleGameService.rollDiceFor("player1"));
    }

    @Test
    public void shouldNotBeAbleToPlayAfterEndingTurn() throws Exception {
        // Given
        SingleGameService singleGameService = new StandardSingleGameService(new EventNotifierTestDouble(new ArrayList<>()));
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.addPlayer("player3");
        singleGameService.startGame();

        // When
        singleGameService.endTurnFor("player1");

        // Then
        assertFalse(singleGameService.rollDiceFor("player1"));
    }

    @Test
    public void shouldBeAbleToQuitGameEvenWhenItsNotPlayersTurn() throws Exception {
        // Given
        ArrayList<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifierTestDouble fakeEventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(fakeEventNotifier);
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.startGame();
        singleGameService.endTurnFor("player1");

        // When
        singleGameService.quitGameFor("player1");

        // Then
        assertThatPlayerHasEvent("player1", Move.QUIT, playerEvents);
    }

    @Test
    public void quitPlayersAreSkippedWhenTurnEnds() throws Exception {
        // Given
        ArrayList<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifierTestDouble fakeEventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(fakeEventNotifier);
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.addPlayer("player3");
        singleGameService.addPlayer("player4");
        singleGameService.startGame();
        singleGameService.quitGameFor("player2");
        singleGameService.quitGameFor("player3");

        // When
        singleGameService.endTurnFor("player1");

        // Then
        assertTrue(singleGameService.rollDiceFor("player4"));
    }

    @Test
    public void gameEndsWhenAllButOnePlayerHaveQuit() throws Exception {
        // Given
        ArrayList<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifierTestDouble fakeEventNotifier = new EventNotifierTestDouble(playerEvents);
        SingleGameService singleGameService = new StandardSingleGameService(fakeEventNotifier);
        singleGameService.addPlayer("player1");
        singleGameService.addPlayer("player2");
        singleGameService.addPlayer("player3");
        singleGameService.addPlayer("player4");
        singleGameService.startGame();
        singleGameService.quitGameFor("player2");
        singleGameService.quitGameFor("player3");

        // When
        singleGameService.quitGameFor("player1");

        // Then
        assertThatPlayerHasEvent("player4", Move.WON, playerEvents);
    }

    private void assertThatPlayerHasEvent(String playerName, Move move, ArrayList<EventNotifierTestDouble.PlayerEvent> listOfPlayerEvents) {
        boolean player1Quit = false;
        for(EventNotifierTestDouble.PlayerEvent playerEvent : listOfPlayerEvents) {
            if(playerEvent.name.equals(playerName) && playerEvent.move.equals(move)) {
                player1Quit = true;
                break;
            }
        }

        assertTrue(player1Quit);
    }
}
