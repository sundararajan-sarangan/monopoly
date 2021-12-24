package monopoly.game;

import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewGameTest {
    @Test
    public void createNewGameAndAcceptNumberOfPlayers() {
        // Given
        StandardBoardMaker standardBoardMaker = new StandardBoardMaker();

        // When && Then
        new Game(3, standardBoardMaker);
    }

    @Test
    public void onlyFirstPlayerCanPlayAfterNewGameCreated() {
        // Given
        StandardBoardMaker boardMaker = new StandardBoardMaker();
        Game game = new Game(4, boardMaker);

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
    public void gameCreatesPlayersOnceAndOnlyOnce() {
        // Given
        StandardBoardMaker boardMaker = new StandardBoardMaker();
        Game game = new Game(3, boardMaker);

        // When
        List<Player> players1 = game.players();
        List<Player> players2 = game.players();

        // Then
        assertEquals(players1, players2);
    }
}
