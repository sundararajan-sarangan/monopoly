package monopoly.board;

public class Board {
    private static final int MIN_TILES = 12;
    private int numberOfTiles;

    public Board(int numberOfTiles) {
        if(numberOfTiles < MIN_TILES) {
            throw new RuntimeException();
        }

        this.numberOfTiles = numberOfTiles;
    }

    public int getNewPosition(int currentPosition, int positionsToAdvanceBy) {
        return (currentPosition + positionsToAdvanceBy) % numberOfTiles;
    }
}
