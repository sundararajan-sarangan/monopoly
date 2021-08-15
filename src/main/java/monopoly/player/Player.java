package monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.money.Cash;
import monopoly.tile.Property;
import monopoly.tile.PropertyGroup;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Board board;
    private final Dice dice;
    public int position;
    public final Cash cash;
    private List<Option> options;

    public Player(int position, Board board, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.position = position;
        this.cash = new Cash(1500);
        this.options = new ArrayList<>();
    }

    public boolean rollDiceAndMove() {
        if(playersTurnToPlay()) {
            board.advancePlayer(this, positionsToAdvanceBy());
            return true;
        }

        return false;
    }

    private boolean playersTurnToPlay() {
        for(Option option : options) {
            if(Move.TURN_TO_PLAY.equals(option.move)) {
                return true;
            }
        }

        return false;
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
        return options;
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public void makeTurnToPlay() {
        Option option = new Option();
        option.move = Move.TURN_TO_PLAY;
        options.add(option);
    }

    public void payRentToOwnerOfPropertyAtCurrentPosition() {
        Property property = board.getPropertyAt(position);
        PropertyGroup propertyGroup = board.getPropertyGroup(property);
        boolean ownerHasMonopoly = propertyGroup.oneOwnerHasMonopoly();
        int rent = property.rent(ownerHasMonopoly);
        this.take(rent);
        board.getPropertyAt(position).owner().give(rent);
        List<Option> newOptions = new ArrayList<>();
        for(Option option : options) {
            if(Move.PAY_RENT.equals(option.move)) {
                continue;
            }

            newOptions.add(option);
        }

        options = newOptions;
    }

    public boolean buyCurrentProperty() {
        Property property = board.getPropertyAt(position);
        this.take(property.cost());
        property.setOwner(this);
        List<Option> newOptions = new ArrayList<>();
        for(Option option : options) {
            if(Move.BUY.equals(option.move)) {
                continue;
            }

            newOptions.add(option);
        }

        options = newOptions;
        return true;
    }
}
