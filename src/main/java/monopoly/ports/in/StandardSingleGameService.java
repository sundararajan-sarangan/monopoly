package monopoly.ports.in;

import monopoly.adapters.out.init.StandardBoardMaker;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.game.StartingPlayerNames;
import monopoly.game.StandardGame;
import monopoly.ports.out.BoardMaker;
import monopoly.ports.out.EventNotifier;

import java.util.ArrayList;
import java.util.List;

public class StandardSingleGameService implements SingleGameService {
    private final EventNotifier eventNotifier;
    private final List<String> playerNames;
    private final Dice dice;
    private final BoardMaker boardMaker;
    private StandardGame game;

    public StandardSingleGameService(EventNotifier eventNotifier) {
        this(eventNotifier, new StandardBoardMaker(), new Dice());
    }

    StandardSingleGameService(EventNotifier eventNotifier, BoardMaker boardMaker, Dice dice) {
        this.eventNotifier = eventNotifier;
        this.playerNames = new ArrayList<>();
        this.boardMaker = boardMaker;
        this.dice = dice;
    }

    @Override
    public void addPlayer(String playerName) throws Exception {
        if(gameHasAlreadyStarted()) {
            throw new Exception("Game already in progress. Cannot add a player!");
        }

        playerNames.add(playerName);
    }

    private boolean gameHasAlreadyStarted() {
        return game != null;
    }

    @Override
    public void startGame() throws Exception {
        game = startNewGameWith(playerNames, boardMaker.makeBoard(), dice);
    }

    private StandardGame startNewGameWith(List<String> playerNames, Board board, Dice dice) throws Exception {
        return new StandardGame(new StartingPlayerNames(playerNames), eventNotifier, board, dice);
    }

    @Override
    public boolean rollDiceFor(String playerName) {
        return game.namesAndPlayers().get(playerName).rollDiceAndMove();
    }

    @Override
    public void endTurnFor(String playerName) {
        game.endTurnFor(playerName);
    }

    @Override
    public void quitGameFor(String playerName) {
        game.quitGameFor(playerName);
    }
}
