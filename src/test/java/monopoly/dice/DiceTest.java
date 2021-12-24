package monopoly.dice;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {
    @Test
    public void rollDice() {
        Dice dice = new Dice();
        DiceResult dr = dice.roll();
        assertNotNull(dr.die1);
        assertNotNull(dr.die2);
    }

    @Test
    public void rollDiceReturnsANumberBetween2And12Inclusive() {
        Dice dice = new Dice();
        assertTrue(dice.roll().value() >= 2 && dice.roll().value() <= 12);
    }

    @Test
    public void rollDiceReturnsARandomNumberBetween2And12Inclusive() {
        Map<Integer, Integer> freq = new HashMap<>();
        Dice dice = new Dice();
        for(int i = 0; i < 1000; i++) {
            DiceResult result = dice.roll();
            int currentFrequency = freq.getOrDefault(result.value(), 0);
            int newFrequency = currentFrequency + 1;
            freq.put(result.value(), newFrequency);
        }

        for(int i = 2; i <= 12; i++) {
            assertTrue(freq.containsKey(i));
        }
    }

    @Test
    public void rollDiceReturnsTwoNumbersEachBetween1And6Inclusive() {
        Dice dice = new Dice();
        DiceResult dr = dice.roll();
        assertTrue(dr.die1.value >= 1 && dr.die1.value <= 6);
        assertTrue(dr.die2.value >= 1 && dr.die2.value <= 6);
    }

    @Test
    public void dieThrowsRuntimeExceptionIfValueOutOfBounds() {
        assertThrows(RuntimeException.class, () -> Die.of(7));
        assertThrows(RuntimeException.class, () -> Die.of(-1));
    }

    @Test
    public void dieResultCanTellItsNotADouble() {
        DiceResult dr = new DiceResult(Die.ONE, Die.TWO);
        assertFalse(dr.isDouble());
    }

    @Test
    public void dieResultCanTellItIsADouble() {
        DiceResult dr = new DiceResult(Die.TWO, Die.TWO);
        assertTrue(dr.isDouble());
    }
}
