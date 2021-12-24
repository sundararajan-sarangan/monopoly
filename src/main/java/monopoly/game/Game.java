package monopoly.game;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;

    public Game(int numberOfPlayers, StandardBoardMaker standardBoardMaker) {
        Board board = standardBoardMaker.makeBoard();
        Dice dice = new Dice();
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(board, dice);
            player.addOption(i == 0 ? Move.TURN_TO_PLAY : Move.CANNOT_PLAY, new Option());
            players.add(player);
        }

        this.players = players;
    }

    public List<Player> players() {
        return players;
    }
}
