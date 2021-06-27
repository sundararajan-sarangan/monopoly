package monopoly.tile;

public enum DevelopedLevel {
    NO_HOUSES, ONE_HOUSE, TWO_HOUSES, THREE_HOUSES, FOUR_HOUSES, HOTEL;

    public DevelopedLevel upgrade() {
        switch (this) {
            case NO_HOUSES:
                return ONE_HOUSE;
            case ONE_HOUSE:
                return TWO_HOUSES;
            case TWO_HOUSES:
                return THREE_HOUSES;
            case THREE_HOUSES:
                return FOUR_HOUSES;
            case FOUR_HOUSES:
                return HOTEL;
            default:
                throw new RuntimeException();
        }
    }
}
