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
    private static Board board;
    private static Player owner;

    @BeforeEach
    public void init() {
        board = new StandardBoardMaker().makeBoard();
        owner = new Player(board, new Dice());
        boardwalk = board.getPropertyAt(39);
        parkPlace = board.getPropertyAt(37);
    }

    @Test
    public void figureOutRentsOnGivenPropertyAndNoMonopoly() {
        boardwalk.setOwner(owner);
        assertEquals(50, board.rentFor(boardwalk));
    }

    @Test
    public void figureOutRentsOnGivenPropertyWhenOwnerHasMonopolyButNoDevelopmentAnywhere() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        assertEquals(100, board.rentFor(boardwalk));
    }

    @Test
    public void figureOutRentsOnGivenPropertyWhenOwnerHasMonopolyAndPropertyIsDeveloped() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        boardwalk.upgrade();
        assertEquals(200, board.rentFor(boardwalk));
        assertEquals(70, board.rentFor(parkPlace));
    }

    @Test
    public void rentShouldBeZeroIfNoneOwnsTheProperty() {
        assertEquals(0, board.rentFor(boardwalk));
    }
}
