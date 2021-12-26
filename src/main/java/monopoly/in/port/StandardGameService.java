package monopoly.in.port;

import monopoly.api.Game;
import monopoly.game.StandardGame;
import monopoly.game.StartingPlayersNames;

import java.util.ArrayList;
import java.util.List;

public class StandardGameService implements GameService {
    List<String> playerNames = new ArrayList<>();
    @Override
    public void addPlayer(String playerName) {
        playerNames.add(playerName);
    }

    @Override
    public Game startGame() throws Exception {
        return new StandardGame(new StartingPlayersNames(playerNames));
    }
}
