public class DiceResult {
    public Die die1;
    public Die die2;
    public DiceResult(Die die1, Die die2) {
        this.die1 = die1;
        this.die2 = die2;
    }

    public int value() {
        return die1.value + die2.value;
    }
}
