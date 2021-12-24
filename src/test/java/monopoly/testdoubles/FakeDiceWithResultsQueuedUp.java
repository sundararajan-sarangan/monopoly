package monopoly.testdoubles;

import monopoly.dice.Dice;
import monopoly.dice.DiceResult;

import java.util.List;

public class FakeDiceWithResultsQueuedUp extends Dice {
    private final List<DiceResult> rolls;
    private int index = -1;

    public FakeDiceWithResultsQueuedUp(List<DiceResult> rolls) {
        this.rolls = rolls;
    }

    @Override
    public DiceResult roll() {
        index++;
        if(index > rolls.size()) {
            index = 0;
        }

        return rolls.get(index);
    }
}
