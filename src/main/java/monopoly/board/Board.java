package monopoly.board;

import monopoly.tile.Property;

import java.util.List;

public class Board {
    private static final int MIN_TILES = 12;
    private final int numberOfTiles;
    private List<Property> properties;

    public Board(int numberOfTiles) {
        if(numberOfTiles < MIN_TILES) {
            throw new RuntimeException();
        }

        this.numberOfTiles = numberOfTiles;
    }

    public Board(List<Property> properties) {
        this(properties.size());
        this.properties = properties;
    }

    public int getNewPosition(int currentPosition, int positionsToAdvanceBy) {
        return (currentPosition + positionsToAdvanceBy) % numberOfTiles;
    }

    public Property getPropertyAt(int i) {
        return properties.get(i);
    }
}
