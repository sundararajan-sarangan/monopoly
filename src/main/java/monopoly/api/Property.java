package monopoly.api;

import monopoly.player.Player;
import monopoly.tile.Group;

public interface Property {
    String name();

    Group group();

    default int rent() {
        return 0;
    }

    default void upgrade() {}

    default int cost() {
        return 0;
    }

    default void setOwner(Player owner) {}

    default Player owner() {
        return null;
    }

    default boolean isUnimproved() {
        return true;
    }

    default void visitedBy(Player player) {}
}
