package monopoly.tile.property;

import monopoly.api.Property;
import monopoly.tile.Group;

public class NoneProperty implements Property {
    protected String name;
    protected Group group;
    public NoneProperty(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Group group() {
        return group;
    }
}
