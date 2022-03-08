package monopoly.ports.in;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManageMultipleGamesWithGameServiceTest {
    @Test
    public void creatingNewGameReturnsAGameId() {
        // Given
        MultiGameService multiGameService = StandardMultiGameService.getInstance();

        // When
        String gameId = multiGameService.prepareNewGame();

        // Then
        assertNotNull(gameId);
    }

    @Test
    public void callingGetInstanceOnStandardMultiGameServiceReturnsASingleton() {
        assertEquals(StandardMultiGameService.getInstance(), StandardMultiGameService.getInstance());
    }

    @Test
    public void shouldReturnARandomFourCharacterGameCodeWhenPrepareNewGameIsCalled() {
        MultiGameService gameService = StandardMultiGameService.getInstance();
        String gameCode = gameService.prepareNewGame();
        assertEquals(4, gameCode.length());
    }
}
