package monopoly.ports.in;

import monopoly.ports.out.EventNotifier;
import monopoly.ports.out.EventNotifierTestDouble;
import monopoly.ports.out.NotificationEvent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceTest {

    @Test
    public void startGameWithSomePlayersTest() throws Exception {
        GameService gameService = new StandardGameService(new EventNotifierTestDouble(new ArrayList<>()));
        gameService.addPlayer("Zip");
        gameService.addPlayer("Zop");
        gameService.addPlayer("Zap");
        gameService.startGame();
    }

    @Test
    public void startingANewGameNotifiesFirstPlayerItsTheirTurnToPlay() throws Exception {
        List<EventNotifierTestDouble.PlayerEvent> playerEvents = new ArrayList<>();
        EventNotifier eventNotifier = new EventNotifierTestDouble(playerEvents);
        GameService gameService = new StandardGameService(eventNotifier);
        gameService.addPlayer("bleep");
        gameService.addPlayer("bloop");
        gameService.startGame();
        assertEquals(1, playerEvents.size());

        assertEquals("bleep", playerEvents.get(0).name);
        assertEquals(NotificationEvent.TURN_TO_PLAY, playerEvents.get(0).event);
    }
}
