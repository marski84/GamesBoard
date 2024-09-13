//package org.localhost.gamesboard.api;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.localhost.gamesboard.Dto.GameDto;
//import org.localhost.gamesboard.exceptions.GameNotFoundException;
//import org.localhost.gamesboard.Game.BaseGameFacade;
//import org.localhost.gamesboard.model.Game;
//import org.localhost.gamesboard.Game.BaseGameService;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class BaseGameFacadeTest {
//
//    @Mock
//    private BaseGameService baseGameService;
//
//    private BaseGameFacade objectUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
////        objectUnderTest = new GameFacadeImpl(gameServiceImpl);
//    }
//
//    @Test
//    @DisplayName("It should create a new game")
//    void createGame() {
//        // Given
//        String gameName = "Test Game";
//        Game expectedGame = new Game();
//        expectedGame.setGameName(gameName);
//        when(baseGameService.registerNewGame(any(Game.class))).thenReturn(expectedGame);
//
//        // When
//        GameDto result = objectUnderTest.createGame(gameName);
//
//        // Then
//        assertEquals(gameName, result.getGameName());
//        verify(baseGameService).registerNewGame(any(Game.class));
//    }
//
//    @Test
//    @DisplayName("It should start the game")
//    void startGame() {
//        // Given
//        int gameId = 1;
//        doNothing().when(baseGameService).startGame(gameId);
//
//        // When
//        objectUnderTest.startGame(gameId);
//
//        // Then
//        verify(baseGameService).startGame(gameId);
//    }
//
//    @Test
//    @DisplayName("It should throw when trying to start a game which does not exist")
//    void shouldThrowWhenGameNotFound() {
//        // Given
//        int gameId = 1;
//        doThrow(new GameNotFoundException("Game not found")).when(baseGameService).startGame(gameId);
//
//        // When & Then
//        assertThrows(GameNotFoundException.class, () -> objectUnderTest.startGame(gameId));
//    }
//
//    @Test
//    @DisplayName("It should finish the game")
//    void endGame() {
//        // Given
//        int gameId = 1;
//        doNothing().when(baseGameService).endGame(gameId);
//
//        // When
//        objectUnderTest.endGame(gameId);
//
//        // Then
//        verify(baseGameService).endGame(gameId);
//    }
//
//    // Dodaj pozostałe testy dla innych metod...
//}