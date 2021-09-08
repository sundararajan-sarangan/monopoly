package monopoly.tile.property;

import monopoly.api.Property;
import monopoly.player.Player;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Group;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;
import monopoly.turn.Move;
import monopoly.turn.Option;

public class RealEstateProperty implements Property {
    private final Rents rents;
    private final String name;
    private final int cost;
    private final Group group;
    private Player owner;

    private DevelopedLevel developedLevel;

    public RealEstateProperty(String name, Group group, Costs costs, Rents rents, DevelopedLevel developedLevel) {
        this.name = name;
        this.cost = costs.cost();
        this.rents = rents;
        this.developedLevel = developedLevel;
        this.group = group;
    }

    @Override
    public int rent() {
        return rents.developedLevelToRentMap().get(developedLevel);
    }

    @Override
    public void upgrade() {
        developedLevel = developedLevel.upgrade();
    }

    @Override
    public int cost() {
        return cost;
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
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public Player owner() {
        return owner;
    }

    @Override
    public boolean isUnimproved() {
        return this.developedLevel.equals(DevelopedLevel.NO_HOUSES);
    }

    @Override
    public void visitedBy(Player player) {
        if (player.equals(owner)) {
            return;
        }

        player.addOption(owner == null ? Move.BUY : Move.PAY_RENT, new Option());
    }
}
