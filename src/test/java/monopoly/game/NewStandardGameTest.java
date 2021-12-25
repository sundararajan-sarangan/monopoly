package monopoly.game;

import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NewStandardGameTest {
    @Test
    public void gameAcceptsPlayerNames() throws Exception {
        new StandardGame(new StartingPlayersNames("Player1", "Player2"));
    }

    @Test
    public void onlyFirstPlayerCanPlayAfterNewGameCreated() throws Exception {
        // Given
        StandardGame game = new StandardGame(new StartingPlayersNames("Beep", "Bop", "Boop", "Burp"));

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
        StandardGame game = new StandardGame(new StartingPlayersNames("Beep", "Bop", "Boop"));

        // When
        List<Player> players1 = game.players();
        List<Player> players2 = game.players();

        // Then
        assertEquals(players1, players2);
    }

    @Test
    public void gameAssociatesNamesWithPlayers() throws Exception {
        // Given
        StandardGame game = new StandardGame(new StartingPlayersNames("Beep", "Bop", "Boop"));

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
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames("Bleep")));
    }

    @Test
    public void gameDoesntAcceptMoreThan4Players() {
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames("Beep", "Bop", "Boop", "Burp", "Zorg")));
    }

    @Test
    public void playerNamesShouldHaveAtleastOneCharacter() {
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames("", "lol", "123")));
    }

    @Test
    public void playerNamesShouldNotBeNull() {
        assertThrows(Exception.class, () -> new StandardGame(new StartingPlayersNames("bleep", "bloop", null)));
    }
}
