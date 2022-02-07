package monopoly.ports.in;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.game.StandardGame;
import monopoly.game.StartingPlayersNames;
import monopoly.player.Player;
import monopoly.ports.out.EventNotifier;

import java.util.ArrayList;
import java.util.List;

public class StandardGameService implements GameService {
    private final EventNotifier eventNotifier;
    private final List<String> playerNames;
    private StandardGame game;

    public StandardGameService(EventNotifier eventNotifier) {
        this(eventNotifier, new StandardBoardMaker().makeBoard(), new Dice());
    }

    StandardGameService(EventNotifier eventNotifier, Board board, Dice dice) {
        this.eventNotifier = eventNotifier;
        this.playerNames = new ArrayList<>();
    }

    @Override
    public void addPlayer(String playerName) {
        playerNames.add(playerName);
    }

    @Override
    public void startGame() throws Exception {
        game = new StandardGame(new StartingPlayersNames(playerNames), eventNotifier);
    }

    @Override
    public boolean rollDiceFor(String playerName) {
        Player player = game.namedPlayers().get(playerName);
        return player.rollDiceAndMove();
    }
}
