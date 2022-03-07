package monopoly.game;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StandardGame {
    private final Map<String, Player> namedPlayers;
    private final Players players;

    public StandardGame(Players players, EventNotifier eventNotifier) {
        this(players, eventNotifier, new StandardBoardMaker().makeBoard(), new Dice());
    }

    public StandardGame(Players players, EventNotifier eventNotifier, Board board, Dice dice) {
        this.players = players;
        Map<String, Player> namedPlayers = new LinkedHashMap<>();
        for(String name : players.getNames()) {
            Player player = new Player(name, board, dice, eventNotifier);
            player.addOption(namedPlayers.isEmpty() ? Move.TURN_TO_PLAY : Move.CANNOT_PLAY, new Option());
            namedPlayers.put(name, player);
        }

        this.namedPlayers = namedPlayers;
    }

    public List<Player> players() {
        return new ArrayList<>(namedPlayers.values());
    }

    public Map<String, Player> namesAndPlayers() {
        return namedPlayers;
    }

    public void endTurnFor(String playerName) {
        int currentPlayersIndex = players.getNames().indexOf(playerName);
        namedPlayers.get(playerName).endTurn();
        currentPlayersIndex++;
        if(currentPlayersIndex >= players.getNames().size()) {
            currentPlayersIndex = 0;
        }

        String newPlayerName = players.getNames().get(currentPlayersIndex);
        namedPlayers.get(newPlayerName).addOption(Move.TURN_TO_PLAY, new Option());
    }
}
