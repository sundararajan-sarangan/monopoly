package monopoly.tile.property;

import monopoly.player.Player;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

public class GoToJail extends NoneProperty {
    public GoToJail(String name, Group group) {
        super(name, group);
    }

    @Override
    public void visitedBy(Player player) {
        player.position = 10;
    }
}
