package monopoly.out.port;

public interface EventNotifier {
    void sendNotification(String name, NotificationEvent event);
}
