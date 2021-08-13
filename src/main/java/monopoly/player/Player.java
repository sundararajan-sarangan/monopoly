package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.money.Cash;
import monopoly.tile.Property;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.List;

public class Player {
    private final Board board;
    private final Dice dice;
    public int position;
    public final Cash cash;

    public Player(int position, Board board, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.position = position;
        this.cash = new Cash(1500);
    }

    public void rollDiceAndMove() {
        position = board.getNewPosition(position, positionsToAdvanceBy());
    }

    private int positionsToAdvanceBy() {
        return dice.roll().value();
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

    public List<Option> currentOptions() {
        Option option = new Option();
        Player propertyOwner = board.getPropertyAt(this.position).owner();
        if(propertyOwner == null) {
            option.move = Move.BUY;
        } else if (this.equals(propertyOwner)){
            return List.of();
        } else {
            option.move = Move.PAY_RENT;
        }

        return List.of(option);
    }
}
