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

    public StandardGame(StartingPlayersNames startingPlayersNames, EventNotifier eventNotifier) {
        this(startingPlayersNames, eventNotifier, new StandardBoardMaker().makeBoard(), new Dice());
    }

    StandardGame(StartingPlayersNames startingPlayersNames, EventNotifier eventNotifier, Board board, Dice dice) {
        Map<String, Player> namedPlayers = new LinkedHashMap<>();
        for(String name : startingPlayersNames.getNames()) {
            Player player = new Player(name, board, dice, eventNotifier);
            player.addOption(namedPlayers.isEmpty() ? Move.TURN_TO_PLAY : Move.CANNOT_PLAY, new Option());
            namedPlayers.put(name, player);
        }

        this.namedPlayers = namedPlayers;
    }

    public List<Player> players() {
        return new ArrayList<>(namedPlayers.values());
    }

    public Map<String, Player> namedPlayers() {
        return namedPlayers;
    }
}
