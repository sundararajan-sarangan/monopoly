package monopoly.player;

import monopoly.dice.DiceResult;

import java.util.ArrayList;
import java.util.List;

public class DieRollHistory {
    private final List<DiceResult> history = new ArrayList<>();

    public void add(DiceResult diceResult) {
        this.history.add(diceResult);
    }

    public boolean areLastThreeDoubles() {
        int size = history.size();
        return size >= 3 &&
                history.get(size - 3).isDouble() &&
                history.get(size - 2).isDouble() &&
                history.get(size - 1).isDouble();
    }
}
