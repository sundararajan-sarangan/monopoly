public enum Die {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    public final int value;

    Die(int value) {
        this.value = value;
    }

    public static Die of(int number) {
        switch (number) {
            case 1: return ONE;
            case 2: return TWO;
            case 3: return THREE;
            case 4: return FOUR;
            case 5: return FIVE;
            case 6: return SIX;
            default:
                throw new RuntimeException();
        }
    }
}
