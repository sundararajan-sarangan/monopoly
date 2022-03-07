package monopoly.ports.in;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.game.Players;
import monopoly.game.StandardGame;
import monopoly.ports.out.BoardMaker;
import monopoly.ports.out.EventNotifier;

import java.util.ArrayList;
import java.util.List;

public class StandardGameService implements GameService {
    private final EventNotifier eventNotifier;
    private final List<String> playerNames;
    private final Dice dice;
    private final BoardMaker boardMaker;
    private StandardGame game;

    public StandardGameService(EventNotifier eventNotifier) {
        this(eventNotifier, new StandardBoardMaker(), new Dice());
    }

    StandardGameService(EventNotifier eventNotifier, BoardMaker boardMaker, Dice dice) {
        this.eventNotifier = eventNotifier;
        this.playerNames = new ArrayList<>();
        this.boardMaker = boardMaker;
        this.dice = dice;
    }

    @Override
    public void addPlayer(String playerName) {
        playerNames.add(playerName);
    }

    @Override
    public void startGame() throws Exception {
        game = startNewGameWith(playerNames, boardMaker.makeBoard(), dice);
    }

    private StandardGame startNewGameWith(List<String> playerNames, Board board, Dice dice) throws Exception {
        return new StandardGame(new Players(playerNames), eventNotifier, board, dice);
    }

    @Override
    public boolean rollDiceFor(String playerName) {
        return game.namesAndPlayers().get(playerName).rollDiceAndMove();
    }

    @Override
    public void endTurnFor(String playerName) {
        game.endTurnFor(playerName);
    }
}
