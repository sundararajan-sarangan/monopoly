package monopoly.game;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.Player;
import monopoly.player.Players;
import monopoly.ports.out.EventNotifier;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.List;
import java.util.Map;

public class StandardGame {
    private final Players players;

    public StandardGame(StartingPlayerNames startingPlayerNames, EventNotifier eventNotifier) {
        this(startingPlayerNames, eventNotifier, new StandardBoardMaker().makeBoard(), new Dice());
    }

    public StandardGame(StartingPlayerNames startingPlayerNames, EventNotifier eventNotifier, Board board, Dice dice) {
        this.players = new Players();
        boolean isFirstPlayer = true;
        for(String name : startingPlayerNames.getNames()) {
            Player player = new Player(name, board, dice, eventNotifier);
            player.addOption(isFirstPlayer ? Move.TURN_TO_PLAY : Move.CANNOT_PLAY, new Option());
            isFirstPlayer = false;
            players.addPlayer(player);
        }
    }

    public List<Player> players() {
        return players.list();
    }

    public Map<String, Player> namesAndPlayers() {
        return players.nameToPlayerMap();
    }

    public void endTurnFor(String playerName) {
        players.endTurn();
    }

    public void quitGameFor(String playerName) {
        players.quitGameFor(playerName);
    }
}
