package org.localhost.gamesboard.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.repository.GameRepository;
import org.localhost.gamesboard.service.GameService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.GenericArrayType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class GameFacadeImplTest {

    @Mock
    private GameService gameService;
    private GameFacadeImpl objectUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectUnderTest = new GameFacadeImpl(gameService);
    }



    @Test
    @DisplayName("It should create a new game")
    void createGame() {
        // Given
        String gameName = "Test Game";

        // When
        objectUnderTest.createGame(gameName);

        // Then
        verify(gameService).registerNewGame(any(Game.class));
    }

    @Test
    void startGame() {
    }

    @Test
    void endGame() {
    }

    @Test
    void addPlayer() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void saveGameScore() {
    }

    @Test
    void getGameId() {
    }

    @Test
    void getGameById() {
    }
}