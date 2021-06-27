package test.monopoly.tile;

import monopoly.tile.*;
import monopoly.tile.money.Costs;
import monopoly.tile.money.Rents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyUpgradesTest {
    private static Property boardWalk;

    @BeforeEach
    public void init() {
        boardWalk = new Property("BoardWalk",
                ColorGroup.DARK_BLUE,
                new Costs(440, 200, 200, 200),
                new Rents(Map.of(DevelopedLevel.NO_HOUSES, 50,
                        DevelopedLevel.ONE_HOUSE, 200,
                        DevelopedLevel.TWO_HOUSES, 600,
                        DevelopedLevel.THREE_HOUSES, 1400,
                        DevelopedLevel.FOUR_HOUSES, 1700,
                        DevelopedLevel.HOTEL, 2000)), DevelopedLevel.NO_HOUSES);
    }

    @Test
    public void testPropertyRentWithNoHouses() {
        assertEquals(50, boardWalk.rent(false));
    }

    @Test
    public void testPropertyRentWithOneHouse() {
        boardWalk.upgrade();
        assertEquals(200, boardWalk.rent(false));
    }

    @Test
    public void testPropertyRentWithTwoHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(600, boardWalk.rent(false));
    }

    @Test
    public void testPropertyRentWithThreeHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1400, boardWalk.rent(false));
    }

    @Test
    public void testPropertyRentWithFourHouses() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(1700, boardWalk.rent(false));
    }

    @Test
    public void testPropertyRentWithHotel() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertEquals(2000, boardWalk.rent(false));
    }

    @Test
    public void testUpgradingMoreThanAllowed() {
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        boardWalk.upgrade();
        assertThrows(RuntimeException.class, () -> boardWalk.upgrade());
    }
}
