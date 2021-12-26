package monopoly.api;

import monopoly.player.Player;

import java.util.List;
import java.util.Map;

public interface Game {
    List<Player> players();

    Map<String, Player> namedPlayers();
}
