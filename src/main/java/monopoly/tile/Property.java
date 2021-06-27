package monopoly.tile;

import monopoly.player.Player;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;

public class Property {
    private Rents rents;
    private String name;
    private int cost;
    private ColorGroup colorGroup;
    private Player owner;

    private DevelopedLevel developedLevel;
    public Property(String name, ColorGroup colorGroup, Costs costs, Rents rents, DevelopedLevel developedLevel) {
        this.name = name;
        this.cost = costs.cost();
        this.rents = rents;
        this.developedLevel = developedLevel;
        this.colorGroup = colorGroup;
    }

    public int rent() {
        return getRentForTheCurrentlyDevelopedLevel();
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

    public ColorGroup colorGroup() {
        return colorGroup;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player owner() {
        return owner;
    }
}
