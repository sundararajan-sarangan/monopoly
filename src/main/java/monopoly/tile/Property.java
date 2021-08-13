package monopoly.tile;

import monopoly.player.Player;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

public class Property {
    private final Rents rents;
    private final String name;
    private final int cost;
    private final Group group;
    private Player owner;

    private DevelopedLevel developedLevel;
    public Property(String name, Group group, Costs costs, Rents rents, DevelopedLevel developedLevel) {
        this.name = name;
        this.cost = costs.cost();
        this.rents = rents;
        this.developedLevel = developedLevel;
        this.group = group;
    }

    public int rent(boolean ownerHasMonopoly) {
        int rent = getRentForTheCurrentlyDevelopedLevel();
        if(isUnimproved() && ownerHasMonopoly) {
            rent = rent * 2;
        }

        return rent;
    }

    private Integer getRentForTheCurrentlyDevelopedLevel() {
        return rents.developedLevelToRentMap().get(developedLevel);
    }

    public void upgrade() {
        developedLevel = developedLevel.upgrade();
    }

    public int cost() {
        return cost;
    }

    public String name() {
        return name;
    }

    public Group group() {
        return group;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player owner() {
        return owner;
    }

    public boolean isUnimproved() {
        return this.developedLevel.equals(DevelopedLevel.NO_HOUSES);
    }
}
