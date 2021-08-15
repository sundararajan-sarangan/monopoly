package monopoly.board;

import monopoly.player.Player;
import monopoly.tile.Group;
import monopoly.tile.Property;
import monopoly.tile.PropertyGroup;

import java.util.ArrayList;
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

    public void advancePlayer(Player player, int positionsToAdvanceBy) {
        int oldPosition = player.position;
        player.position = this.getNewPosition(player.position, positionsToAdvanceBy);
        if(passedGo(player, oldPosition)) {
            player.give(200);
        }

        Property property = this.getPropertyAt(player.position);
        property.visitedBy(player);
    }

    public PropertyGroup getPropertyGroup(Property property) {
        Group group = property.group();
        List<Property> propertiesInTheSameGroup = new ArrayList<>();
        for(Property p : properties) {
            if(group.equals(p.group())) {
                propertiesInTheSameGroup.add(p);
            }
        }

        return new PropertyGroup(group, propertiesInTheSameGroup);
    }

    private boolean passedGo(Player player, int oldPosition) {
        return player.position < oldPosition;
    }
}
