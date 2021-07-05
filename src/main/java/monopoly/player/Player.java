package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.money.Cash;

public class Player {
    private final Board board;
    private final Dice dice;
    private int position;
    private final Cash cash;

    public Player(int position, Board board, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.position = position;
        this.cash = new Cash(1500);
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

    public Cash cash() {
        return cash;
    }

    public void give(int money) {
        cash.add(money);
    }

    public void take(int money) {
        cash.subtract(money);
    }

    public boolean inTheRed() {
        return cash.isNegativeBalance();
    }
}
