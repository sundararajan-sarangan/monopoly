package monopoly.player;

import monopoly.dice.DiceResult;
import monopoly.dice.Die;

import java.util.ArrayList;
import java.util.List;

public class DieRollHistory {
    private final List<DiceResult> history = new ArrayList<>();

    public void add(DiceResult diceResult) {
        this.history.add(diceResult);
    }

    public boolean areLastThreeDoubles() {
        return history.size() >= 3 &&
                isEquals(history.get(0).die1, history.get(0).die2) &&
                isEquals(history.get(1).die1, history.get(1).die2) &&
                isEquals(history.get(2).die1, history.get(2).die2);
    }

    private boolean isEquals(Die die1, Die die2) {
        return die1.equals(die2);
    }
}
