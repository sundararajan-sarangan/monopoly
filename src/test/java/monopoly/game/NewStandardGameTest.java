package monopoly.game;

import monopoly.out.port.EventNotifier;
import monopoly.out.port.EventNotifierTestDouble;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NewStandardGameTest {
    @Test
    public void gameAcceptsPlayerNames() throws Exception {
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        new StandardGame(new StartingPlayersNames(List.of("Player1", "Player2")), eventNotifier);
    }

    @Test
    public void onlyFirstPlayerCanPlayAfterNewGameCreated() throws Exception {
        // Given
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        StandardGame game = new StandardGame(new StartingPlayersNames(List.of("Beep", "Bop", "Boop", "Burp")), eventNotifier);

        // When
        List<Player> players = game.players();

        // Then
        // Player 1 can play.
        assertTrue(players.get(0).hasOptionTo(Move.TURN_TO_PLAY));
        assertFalse(players.get(0).hasOptionTo(Move.CANNOT_PLAY));

        // Player 2 cannot play.
        assertFalse(players.get(1).hasOptionTo(Move.TURN_TO_PLAY));
        assertTrue(players.get(1).hasOptionTo(Move.CANNOT_PLAY));

        // Player 3 cannot play.
        assertFalse(players.get(2).hasOptionTo(Move.TURN_TO_PLAY));
        assertTrue(players.get(2).hasOptionTo(Move.CANNOT_PLAY));

        // Player 4 cannot play.
        assertFalse(players.get(3).hasOptionTo(Move.TURN_TO_PLAY));
        assertTrue(players.get(3).hasOptionTo(Move.CANNOT_PLAY));
    }

    @Test
    public void gameCreatesPlayersOnceAndOnlyOnce() throws Exception {
        // Given
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        StandardGame game = new StandardGame(new StartingPlayersNames(List.of("Beep", "Bop", "Boop")), eventNotifier);

        // When
        List<Player> players1 = game.players();
        List<Player> players2 = game.players();

        // Then
        assertEquals(players1, players2);
    }

    @Test
    public void gameAssociatesNamesWithPlayers() throws Exception {
        // Given
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        StandardGame game = new StandardGame(new StartingPlayersNames(List.of("Beep", "Bop", "Boop")), eventNotifier);

        // When
        Map<String, Player> namedPlayers = game.namedPlayers();
        assertEquals(3, namedPlayers.size());
        assertNotNull(namedPlayers.get("Beep"));
        assertNotNull(namedPlayers.get("Boop"));
        assertNotNull(namedPlayers.get("Bop"));
        assertNull(namedPlayers.get("Random1"));
    }

    @Test
    public void gameDoesntAcceptLessThen2Players() {
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames(List.of("Bleep")), eventNotifier));
    }

    @Test
    public void gameDoesntAcceptMoreThan4Players() {
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames(List.of("Beep", "Bop", "Boop", "Burp", "Zorg")), eventNotifier));
    }

    @Test
    public void playerNamesShouldHaveAtleastOneCharacter() {
        EventNotifier eventNotifier = new EventNotifierTestDouble(new ArrayList<>());
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames(List.of("", "lol", "123")), eventNotifier));
    }
}
