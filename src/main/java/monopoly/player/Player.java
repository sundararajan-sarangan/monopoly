package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;

public class Player {
    private final Board board;
    private final Dice dice;
    private int position;

    public Player(int position, Board board, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.position = position;
    }

    public int position() {
        return position;
    }

    public void rollDiceAndMove() {
        position = board.getNewPosition(position, positionsToAdvanceBy());
    }

    private int positionsToAdvanceBy() {
        return dice.roll().value();
    }
}
