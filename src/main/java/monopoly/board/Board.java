package monopoly.board;

import monopoly.api.Property;
import monopoly.dice.DiceResult;
import monopoly.player.Player;
import monopoly.tile.Group;
import monopoly.tile.PropertyGroup;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Property> properties;

    public Board(List<Property> properties) {
        this.properties = properties;
    }

    private int getNewPosition(int currentPosition, int positionsToAdvanceBy) {
        return (currentPosition + positionsToAdvanceBy) % properties.size();
    }

    public Property getPropertyAt(int position) {
        return properties.get(position);
    }

    public void advancePlayer(Player player, DiceResult diceResult) {
        int oldPosition = player.position;
        player.position = getNewPosition(player.position, diceResult.value());
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

    public int rentFor(Property property) {
        if(null == property.owner()) {
            return 0;
        }

        boolean ownerHasMonopoly = getPropertyGroup(property).oneOwnerHasMonopoly();
        int rent = property.rent();
        if(property.isUnimproved() && ownerHasMonopoly) {
            rent = rent * 2;
        }

        return rent;
    }
}
