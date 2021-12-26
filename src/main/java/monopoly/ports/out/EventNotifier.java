package monopoly.ports.out;

public interface EventNotifier {
    void sendNotification(String name, NotificationEvent event);
}
