package monopoly.tile;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyGroup {
    ColorGroup colorGroup;
    List<Property> properties;

    public PropertyGroup(ColorGroup colorGroup, List<Property> properties) {
        this.colorGroup = colorGroup;
        this.properties = properties;
    }

    public boolean oneOwnerHasMonopoly() {
        return distinctOwnersCount() == 1;
    }

    private int distinctOwnersCount() {
        return properties
                .stream()
                .map(Property::owner)
                .collect(Collectors.toSet())
                .size();
    }

    public int rent(Property property) {
        int rent = property.rent();
        if(oneOwnerHasMonopoly() && isUndeveloped(property)) {
            rent = rent * 2;
        }

        return rent;
    }

    private boolean isUndeveloped(Property property) {
        return property.isUndeveloped();
    }
}
