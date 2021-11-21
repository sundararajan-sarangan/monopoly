package monopoly.player.money;

public class Cash {
    private int cash;
    public Cash(int money) {
        this.cash = money;
    }

    public int value() {
        return cash;
    }

    public void add(int money) {
        cash = cash + money;
    }

    public void subtract(int money) {
        cash = cash - money;
    }

    public boolean isNegativeBalance() {
        return value() < 0;
    }
}
