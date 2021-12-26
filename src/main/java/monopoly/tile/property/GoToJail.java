package monopoly.tile.property;

import monopoly.player.Player;
import monopoly.tile.Group;

public class GoToJail extends NoneProperty {
    private static final int GET_OUT_OF_JAIL_FEE = 50;
    public GoToJail(String name, Group group) {
        super(name, group);
    }

    @Override
    public void visitedBy(Player player) {
        player.position = 10;
        player.takeAway(GET_OUT_OF_JAIL_FEE);
    }
}
