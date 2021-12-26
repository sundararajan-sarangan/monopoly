package monopoly.in.port;

public interface GameService {
    void addPlayer(String playerName);

    void startGame() throws Exception;
}
