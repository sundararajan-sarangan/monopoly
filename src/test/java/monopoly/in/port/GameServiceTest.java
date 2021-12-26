package monopoly.in.port;

import monopoly.api.Game;
import monopoly.player.Player;
import monopoly.turn.Move;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @Test
    public void startGameWithSomePlayersTest() throws Exception {
        GameService gameService = new StandardGameService();
        gameService.addPlayer("Zip");
        gameService.addPlayer("Zop");
        gameService.addPlayer("Zap");
        Game game = gameService.startGame();
        List<Player> players = game.players();
        assertEquals(3, players.size());
        Map<String, Player> namedPlayers = game.namedPlayers();
        assertEquals(3, namedPlayers.size());
        assertNotNull(namedPlayers.get("Zip"));
        assertTrue(namedPlayers.get("Zip").hasOptionTo(Move.TURN_TO_PLAY));
        assertNotNull(namedPlayers.get("Zop"));
        assertNotNull(namedPlayers.get("Zap"));
    }
}
