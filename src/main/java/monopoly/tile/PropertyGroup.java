package monopoly.tile;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyGroup {
    Group group;
    List<Property> properties;

    public PropertyGroup(Group group, List<Property> properties) {
        this.group = group;
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
}
