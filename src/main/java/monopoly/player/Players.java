package monopoly.player;

import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players = new ArrayList<>();
    private int currentPlayersIndex = 0;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> list() {
        return players;
    }

    public Map<String, Player> nameToPlayerMap() {
        Map<String, Player> nameToPlayerMap = new HashMap<>();
        for(Player player : players) {
            nameToPlayerMap.put(player.name, player);
        }

        return nameToPlayerMap;
    }

    public void endTurn() {
        players.get(currentPlayersIndex).endTurn();
        int newPlayersIndex = currentPlayersIndex + 1;
        if (newPlayersIndex >= players.size()) {
            newPlayersIndex = 0;
        }

        while (players.get(newPlayersIndex).hasQuit()) {
            newPlayersIndex++;
            if (newPlayersIndex >= players.size()) {
                newPlayersIndex = 0;
            }
        }

        players.get(newPlayersIndex).addOption(Move.TURN_TO_PLAY, new Option());
        currentPlayersIndex = newPlayersIndex;
    }

    public void quitGameFor(String playerName) {
        nameToPlayerMap().get(playerName).quit();
    }
}
