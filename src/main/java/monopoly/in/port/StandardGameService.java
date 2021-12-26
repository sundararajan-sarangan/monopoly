package monopoly.in.port;

import monopoly.game.StandardGame;
import monopoly.game.StartingPlayersNames;
import monopoly.out.port.EventNotifier;

import java.util.ArrayList;
import java.util.List;

public class StandardGameService implements GameService {
    private final EventNotifier eventNotifier;
    private final List<String> playerNames;

    public StandardGameService(EventNotifier eventNotifier) {
        this.eventNotifier = eventNotifier;
        this.playerNames = new ArrayList<>();
    }

    @Override
    public void addPlayer(String playerName) {
        playerNames.add(playerName);
    }

    @Override
    public void startGame() throws Exception {
        new StandardGame(new StartingPlayersNames(playerNames), eventNotifier);
    }
}
