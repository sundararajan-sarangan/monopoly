package monopoly.init;

import monopoly.player.Player;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.Property;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

public class NoneProperty extends Property {
    public NoneProperty(String name, Group group, Costs costs, Rents rents, DevelopedLevel developedLevel) {
        super(name, group, costs, rents, developedLevel);
    }

    @Override
    public void visitedBy(Player player) {}
}
