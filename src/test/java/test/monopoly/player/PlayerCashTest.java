package test.monopoly.player;

import monopoly.board.Board;
import monopoly.dice.Dice;
import monopoly.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerCashTest {
    @Test
    public void playerHas1500CashByDefault() {
        Player player = new Player(0, new Board(40), new Dice());
        assertEquals(1500, player.cash().value());
    }

    @Test
    public void playerIsGiven200() {
        Player player = new Player(0, new Board(40), new Dice());
        player.give(200);
        assertEquals(1700, player.cash().value());
    }

    @Test
    public void playerPaysBack300() {
        Player player = new Player(0, new Board(40), new Dice());
        player.take(300);
        assertEquals(1200, player.cash().value());
    }

    @Test
    public void playerIsGivenAndThenTakesBack() {
        Player player = new Player(0, new Board(40), new Dice());
        player.give(500);
        player.take(400);
        assertEquals(1600, player.cash().value());
    }

    @Test
    public void playerInTheRed() {
        Player player = new Player(0, new Board(40), new Dice());
        player.take(1700);
        assertTrue(player.inTheRed());
    }

    @Test
    public void brokePlayerNotConsideredToBeInTheRed() {
        Player player = new Player(0, new Board(40), new Dice());
        player.take(player.cash().value());
        assertFalse(player.inTheRed());
        assertEquals(0, player.cash().value());
    }

    @Test
    public void playerWithPositiveCashBalanceNotConsideredToBeInTheRed() {
        Player player = new Player(0, new Board(40), new Dice());
        player.give(500);
        assertFalse(player.inTheRed());
        assertEquals(2000, player.cash().value());
    }
}
