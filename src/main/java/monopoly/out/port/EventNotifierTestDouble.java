package monopoly.out.port;

import java.util.List;

public class EventNotifierTestDouble implements EventNotifier {
    public final List<PlayerEvent> playerEvents;
    public EventNotifierTestDouble(List<PlayerEvent> playerEvents) {
        this.playerEvents = playerEvents;
    }

    @Override
    public void sendNotification(String name, NotificationEvent event) {
        this.playerEvents.add(new PlayerEvent(name, event));
    }

    public static class PlayerEvent {
        public String name;
        public NotificationEvent event;
        public PlayerEvent(String name, NotificationEvent event) {
            this.name = name;
            this.event = event;
        }
    }
}