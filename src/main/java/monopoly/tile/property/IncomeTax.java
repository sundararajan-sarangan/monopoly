package monopoly.tile.property;

import monopoly.player.Player;
import monopoly.tile.Group;

public class IncomeTax extends NoneProperty {
    public IncomeTax(String name, Group group) {
        super(name, group);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Group group() {
        return group;
    }

    @Override
    public void visitedBy(Player player) {
        player.takeAway(200);
    }
}
