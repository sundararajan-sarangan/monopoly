package test.monopoly.tile;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.init.StandardBoardMaker;
import monopoly.player.Player;
import monopoly.tile.Group;
import monopoly.tile.DevelopedLevel;
import monopoly.tile.Property;
import monopoly.tile.PropertyGroup;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyRentsTest {
    private static Property boardwalk;
    private static Property parkPlace;

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
    public void figureOutRentsOnGivenPropertyAndNoMonopoly() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Dice dice = new Dice();
        Player owner = new Player(0, new StandardBoardMaker().makeBoard(), dice);
        boardwalk.setOwner(owner);
        assertEquals(50, boardwalk.rent(propertyGroup.oneOwnerHasMonopoly()));
    }

    @Test
    public void figureOutRentsOnGivenPropertyWhenOwnerHasMonopolyButNoDevelopmentAnywhere() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Dice dice = new Dice();
        Player owner = new Player(0, new StandardBoardMaker().makeBoard(), dice);
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        assertEquals(100, boardwalk.rent(propertyGroup.oneOwnerHasMonopoly()));
    }

    @Test
    public void figureOutRentsOnGivenPropertyWhenOwnerHasMonopolyAndPropertyIsDeveloped() {
        List<Property> properties = List.of(boardwalk, parkPlace);
        PropertyGroup propertyGroup = new PropertyGroup(Group.DARK_BLUE, properties);
        Dice dice = new Dice();
        Player owner = new Player(0, new StandardBoardMaker().makeBoard(), dice);
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        boardwalk.upgrade();
        assertEquals(200, boardwalk.rent(propertyGroup.oneOwnerHasMonopoly()));
    }
}
