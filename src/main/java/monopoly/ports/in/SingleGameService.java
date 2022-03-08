package monopoly.ports.in;

public interface SingleGameService {
    void addPlayer(String playerName) throws Exception;

    void startGame() throws Exception;

    boolean rollDiceFor(String playerName);

    void endTurnFor(String playerName);

    void quitGameFor(String playerName);
}
