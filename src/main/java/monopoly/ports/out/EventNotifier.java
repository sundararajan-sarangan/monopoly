package monopoly.ports.out;

import monopoly.turn.Move;

public interface EventNotifier {
    void sendNotification(String name, Move move);
}
