package monopoly.tile.money;

public class Costs {
    private final int cost;
    private final int mortgageValue;
    private final int house;
    private final int hotel;

    public Costs(int cost, int mortgageValue, int house, int hotel) {
        this.cost = cost;
        this.mortgageValue = mortgageValue;
        this.house = house;
        this.hotel = hotel;
    }

    public int cost() {
        return cost;
    }
    public int mortgageValue() {
        return mortgageValue;
    }
    public int house() {
        return house;
    }
    public int hotel() {
        return hotel;
    }
}
