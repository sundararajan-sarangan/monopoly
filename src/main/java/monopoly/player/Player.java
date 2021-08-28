package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.player.money.Cash;
import monopoly.tile.Property;
import monopoly.tile.PropertyGroup;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.*;

public class Player {
    private final Board board;
    private final Dice dice;
    public int position;
    public final Cash cash;
    private final Map<Move, Option> availableMoves;

    public Player(Board board, Dice dice) {
        this(0, board, dice);
    }

    public Player(int position, Board board, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.position = position;
        this.cash = new Cash(1500);
        this.availableMoves = new HashMap<>();
    }

    public boolean rollDiceAndMove() {
        if(!playersTurnToPlay()) {
            return false;
        }

        board.advancePlayer(this, dice.roll());
        return true;
    }

    private boolean playersTurnToPlay() {
        return availableMoves.containsKey(Move.TURN_TO_PLAY);
    }

    public void give(int money) {
        cash.add(money);
    }

    public void takeAway(int money) {
        cash.subtract(money);
    }

    public boolean inTheRed() {
        return cash.isNegativeBalance();
    }

    public Set<Move> currentOptions() {
        return availableMoves.keySet();
    }

    public void addOption(Move move, Option option) {
        this.availableMoves.put(move, option);
    }

    public void makeTurnToPlay() {
        Option option = new Option();
        availableMoves.put(Move.TURN_TO_PLAY, option);
    }

    public void payRentToOwnerOfPropertyAtCurrentPosition() {
        Property property = board.getPropertyAt(position);
        int rent = board.rentFor(property);
        this.takeAway(rent);
        property.owner().give(rent);
        availableMoves.remove(Move.PAY_RENT);
    }

    public boolean buyCurrentProperty() {
        Property property = board.getPropertyAt(position);
        this.takeAway(property.cost());
        property.setOwner(this);
        availableMoves.remove(Move.BUY);
        return true;
    }

    public boolean hasOption(Move move) {
        return availableMoves.containsKey(move);
    }

    public void endTurn() {
        availableMoves.clear();
    }
}
