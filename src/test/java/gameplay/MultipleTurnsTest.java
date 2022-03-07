package gameplay;

import monopoly.ports.in.GameService;
import monopoly.ports.in.StandardGameService;
import monopoly.testdoubles.EventNotifierTestDouble;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleTurnsTest {
    @Test
    public void firstPlayerCompletesTurnAllowsNextPlayerToPlayTest() throws Exception {
        // Given
        GameService gameService = new StandardGameService(new EventNotifierTestDouble(new ArrayList<>()));
        gameService.addPlayer("player1");
        gameService.addPlayer("player2");
        gameService.startGame();
        gameService.rollDiceFor("player1");

        // When
        gameService.endTurnFor("player1");

        // Then
        assertTrue(gameService.rollDiceFor("player2"));
    }

    @Test
    public void twoPlayersCompleteTurnAllowsThirdPlayerToPlayTest() throws Exception {
        // Given
        GameService gameService = new StandardGameService(new EventNotifierTestDouble(new ArrayList<>()));
        gameService.addPlayer("player1");
        gameService.addPlayer("player2");
        gameService.addPlayer("player3");
        gameService.startGame();
        gameService.rollDiceFor("player1");
        gameService.endTurnFor("player1");
        gameService.rollDiceFor("player2");

        // When
        gameService.endTurnFor("player2");

        // Then
        assertTrue(gameService.rollDiceFor("player3"));
    }

    @Test
    public void shouldReturnToFirstPlayerWhenAllPlayersHaveGoneOneRound() throws Exception {
        // Given
        GameService gameService = new StandardGameService(new EventNotifierTestDouble(new ArrayList<>()));
        gameService.addPlayer("player1");
        gameService.addPlayer("player2");
        gameService.addPlayer("player3");
        gameService.startGame();
        gameService.rollDiceFor("player1");
        gameService.endTurnFor("player1");
        gameService.rollDiceFor("player2");
        gameService.endTurnFor("player2");
        gameService.rollDiceFor("player3");

        // When
        gameService.endTurnFor("player3");

        // Then
        assertTrue(gameService.rollDiceFor("player1"));
    }
}
