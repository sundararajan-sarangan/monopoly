package monopoly.in.port;

import monopoly.api.Game;

public interface GameService {
    void addPlayer(String bleep);

    Game startGame() throws Exception;
}
