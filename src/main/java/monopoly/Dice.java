package monopoly;

import java.util.Random;

public class Dice {
    public DiceResult roll() {
        Die die1 = rollDie();
        Die die2 = rollDie();
        return new DiceResult(die1, die2);
    }

    private Die rollDie() {
        return Die.of(getRandomNumberBetween1And6());
    }

    private int getRandomNumberBetween1And6() {
        return new Random().ints(1, 7).findFirst().getAsInt();
    }
}
