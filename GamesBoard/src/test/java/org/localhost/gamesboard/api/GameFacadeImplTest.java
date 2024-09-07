package org.localhost.gamesboard.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.facade.GameFacadeImpl;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.service.GameServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GameFacadeImplTest {

    @Mock
    private GameServiceImpl gameServiceImpl;

    private GameFacadeImpl objectUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        objectUnderTest = new GameFacadeImpl(gameServiceImpl);
    }

    @Test
    @DisplayName("It should create a new game")
    void createGame() {
        // Given
        String gameName = "Test Game";
        Game expectedGame = new Game();
        expectedGame.setGameName(gameName);
        when(gameServiceImpl.registerNewGame(any(Game.class))).thenReturn(expectedGame);

        // When
        GameDto result = objectUnderTest.createGame(gameName);

        // Then
        assertEquals(gameName, result.getGameName());
        verify(gameServiceImpl).registerNewGame(any(Game.class));
    }

    @Test
    @DisplayName("It should start the game")
    void startGame() {
        // Given
        int gameId = 1;
        doNothing().when(gameServiceImpl).startGame(gameId);

        // When
        objectUnderTest.startGame(gameId);

        // Then
        verify(gameServiceImpl).startGame(gameId);
    }

    @Test
    @DisplayName("It should throw when trying to start a game which does not exist")
    void shouldThrowWhenGameNotFound() {
        // Given
        int gameId = 1;
        doThrow(new GameNotFoundException("Game not found")).when(gameServiceImpl).startGame(gameId);

        // When & Then
        assertThrows(GameNotFoundException.class, () -> objectUnderTest.startGame(gameId));
    }

    @Test
    @DisplayName("It should finish the game")
    void endGame() {
        // Given
        int gameId = 1;
        doNothing().when(gameServiceImpl).endGame(gameId);

        // When
        objectUnderTest.endGame(gameId);

        // Then
        verify(gameServiceImpl).endGame(gameId);
    }

    // Dodaj pozostałe testy dla innych metod...
}