package monopoly.ports.in;

public interface GameService {
    void addPlayer(String playerName);

    void startGame() throws Exception;

    boolean rollDiceFor(String playerName);

    void endTurnFor(String playerName);

    void quitGameFor(String playerName);
}
