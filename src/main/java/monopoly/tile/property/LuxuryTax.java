package monopoly.tile.property;

import monopoly.api.Property;
import monopoly.player.Player;
import monopoly.tile.Group;

public class LuxuryTax extends NoneProperty {
    public LuxuryTax(String name, Group group) {
        super(name, group);
    }

    @Override
    public void visitedBy(Player player) {
        player.takeAway(100);
    }
}
