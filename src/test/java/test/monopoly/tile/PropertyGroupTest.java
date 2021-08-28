package test.monopoly.tile;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.Player;
import monopoly.tile.*;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyGroupTest {
    private static Property boardwalk;
    private static Property parkPlace;
    private static final Board DUMMY_BOARD = new Board(null);
    private static final Dice DUMMY_DICE = new Dice();

    @BeforeEach
    public void init() {
        boardwalk = new Property("Board Walk",
                Group.DARK_BLUE,
                new Costs(440, 200, 200, 200),
                new Rents(Map.of(DevelopedLevel.NO_HOUSES, 50,
                        DevelopedLevel.ONE_HOUSE, 200,
                        DevelopedLevel.TWO_HOUSES, 600,
                        DevelopedLevel.THREE_HOUSES, 1400,
                        DevelopedLevel.FOUR_HOUSES, 1700,
                        DevelopedLevel.HOTEL, 2000)), DevelopedLevel.NO_HOUSES);

        parkPlace = new Property("Park Place",
                Group.DARK_BLUE,
                new Costs(350, 175, 200, 200),
                new Rents(Map.of(DevelopedLevel.NO_HOUSES, 35,
                        DevelopedLevel.ONE_HOUSE, 175,
                        DevelopedLevel.TWO_HOUSES, 500,
                        DevelopedLevel.THREE_HOUSES, 1100,
                        DevelopedLevel.FOUR_HOUSES, 1300,
                        DevelopedLevel.HOTEL, 1500)), DevelopedLevel.NO_HOUSES);
    }

    @Test
    public void sameOwnerForAllProperties() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Player owner = new Player(DUMMY_BOARD, DUMMY_DICE);
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        assertTrue(propertyGroup.oneOwnerHasMonopoly());
    }

    @Test
    public void differentOwnerForProperties() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Player owner1 = new Player(DUMMY_BOARD, DUMMY_DICE);
        Player owner2 = new Player(DUMMY_BOARD, DUMMY_DICE);
        boardwalk.setOwner(owner1);
        boardwalk.setOwner(owner2);
        assertFalse(propertyGroup.oneOwnerHasMonopoly());
    }

    @Test
    public void somePropertiesUnOwned() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Player owner = new Player(DUMMY_BOARD, DUMMY_DICE);
        boardwalk.setOwner(owner);
        assertFalse(propertyGroup.oneOwnerHasMonopoly());
    }
}
