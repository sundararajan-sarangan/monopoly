package monopoly.tile.property;

import monopoly.api.Property;
import monopoly.player.Player;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

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
