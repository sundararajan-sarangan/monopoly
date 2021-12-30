package monopoly.testdoubles;

import monopoly.ports.out.EventNotifier;
import monopoly.turn.Move;

import java.util.List;

public class EventNotifierTestDouble implements EventNotifier {
    public final List<PlayerEvent> playerEvents;
    public EventNotifierTestDouble(List<PlayerEvent> playerEvents) {
        this.playerEvents = playerEvents;
    }

    @Override
    public void sendNotification(String name, Move move) {
        this.playerEvents.add(new PlayerEvent(name, move));
    }

    public static class PlayerEvent {
        public String name;
        public Move move;
        public PlayerEvent(String name, Move move) {
            this.name = name;
            this.move = move;
        }
    }
}