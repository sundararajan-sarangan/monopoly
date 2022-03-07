package monopoly.player;

import monopoly.api.Property;
import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.dice.DiceResult;
import monopoly.player.money.Cash;
import monopoly.ports.out.EventNotifier;
import monopoly.turn.Move;
import monopoly.turn.Option;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player {
    private final Board board;
    private final Dice dice;
    private final EventNotifier eventNotifier;
    public final String name;
    public int position;
    public final Cash cash;
    private final Map<Move, Option> availableMoves;
    private final DieRollHistory dieRollHistory;

    public Player(String name, Board board, Dice dice, EventNotifier eventNotifier) {
        this.name = name;
        this.dice = dice;
        this.board = board;
        this.cash = new Cash(1500);
        this.availableMoves = new HashMap<>();
        availableMoves.put(Move.QUIT, new Option());
        this.dieRollHistory = new DieRollHistory();
        this.eventNotifier = eventNotifier;
    }

    public boolean rollDiceAndMove() {
        if (!playersTurnToPlay()) {
            return false;
        }

        DiceResult diceResult = dice.roll();
        dieRollHistory.add(diceResult);

        if (dieRollHistory.areLastThreeDoubles()) {
            position = 10;
            this.takeAway(50);
            return true;
        }

        board.advancePlayer(this, diceResult);
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
        if(cash.isNegativeBalance()) {
            availableMoves.put(Move.GO_BUST, new Option());
        }
    }

    public boolean inTheRed() {
        return cash.isNegativeBalance();
    }

    public Set<Move> currentOptions() {
        return availableMoves.keySet();
    }

    public void addOption(Move move, Option option) {
        availableMoves.put(move, option);
        eventNotifier.sendNotification(name, move);
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

    public boolean hasOptionTo(Move move) {
        return availableMoves.containsKey(move);
    }

    public void endTurn() {
        availableMoves.clear();
    }

    public boolean canBuy() {
        return availableMoves.containsKey(Move.BUY);
    }

    public boolean canPayRent() {
        return availableMoves.containsKey(Move.PAY_RENT);
    }
}
