package org.localhost.gamesboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.exceptions.GameAlreadyFinishedException;
import org.localhost.gamesboard.exceptions.GameAlreadyStartedException;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.exceptions.GameNotStartedException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.repository.GameRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private final int NOT_EXISTING_GAME_ID = 10;


    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl objectUnderTest;

    private Game testGame;

    @BeforeEach
    void setUp() {
        testGame = new Game();
        testGame.setId(1);
        testGame.setGameName("Test game");
    }

    @Test
    @DisplayName("registerNewGame should create a new game")
    void registerNewGame() {
//        given
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);
//        when
        Game testResult = objectUnderTest.registerNewGame(testGame);
//        then
        assertEquals(testGame.getId(), testResult.getId());

        verify(gameRepository).save(testGame);
    }

    @Test
    @DisplayName("registerNewGame should throw game is null")
    void registerNewGameShouldThrowWhenNull() {
//        given, when, then
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerNewGame(null));
    }

    @Test
    @DisplayName("registerNewGame should throw game name is null")
    void registerNewGameShouldThrowWhenGameNameIsNull() {
//        given, when, then
        testGame.setGameName(null);
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerNewGame(testGame));
    }


    @Test
    @DisplayName("startGame should start a game")
    void startGame() {
//        given
        when(gameRepository.findById(testGame.getId())).thenReturn(Optional.of(testGame));
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);
//        when
       Game testResult = objectUnderTest.startGame(testGame.getId());
//        then
        assertEquals(testGame.getId(), testResult.getId());
        assertEquals(testGame.getGameName(), testResult.getGameName());
        assertNotNull(testResult.getGameStartDate());
    }

    @Test
    @DisplayName("startGame should throw when trying to start already started game")
    void startGameShouldThrowWhenTryingToStartAlreadyStartedGame() {
//        given
        testGame.setGameStartDate(LocalDateTime.now());
        when(gameRepository.findById(testGame.getId())).thenReturn(Optional.of(testGame));
//        when, then
        assertThrows(GameAlreadyStartedException.class, () -> objectUnderTest.startGame(testGame.getId()));
    }

    @Test
    @DisplayName("registerNewGame should throw when trying to start non existing gaame")
    void startGameShouldThrowWhenGameNameIsNotCreated() {
//        given, when, then
        assertThrows(GameNotFoundException.class, () -> objectUnderTest.startGame(NOT_EXISTING_GAME_ID));
    }


    @Test
    @DisplayName("endGame should finish game")
    void endGame() {
//        given
        testGame.setGameStartDate(LocalDateTime.now());
        when(gameRepository.findById(testGame.getId())).thenReturn(Optional.of(testGame));
        when(gameRepository.save(any(Game.class))).thenReturn(testGame);
//        when
        Game testResult = objectUnderTest.endGame(testGame.getId());
//        then
        assertEquals(testGame.getId(), testResult.getId());
        assertNotNull(testResult.getGameFinishDate());
        verify(gameRepository).save(testGame);
    }

    @Test
    @DisplayName("endGame should throw when trying to finish not started game")
    void endGameShouldThrowWhenTryingToFinishNotStartedGame() {
//        given, when,
        when(gameRepository.findById(testGame.getId())).thenReturn(Optional.of(testGame));
        assertThrows(GameNotStartedException.class, () -> objectUnderTest.endGame(testGame.getId()));
    }


    @Test
    @DisplayName("endGame should throw when trying to finish already started game")
    void endGameShouldThrowWhenTryingToFinishAlreadyFinishedGame() {
//        given, when,
        testGame.setGameStartDate(LocalDateTime.now());
        testGame.setGameFinishDate(LocalDateTime.now());
        when(gameRepository.findById(testGame.getId())).thenReturn(Optional.of(testGame));
        assertThrows(GameAlreadyFinishedException.class, () -> objectUnderTest.endGame(testGame.getId()));
    }

    @Test
    void getGameById() {
//        given

//        when

//        then
    }
}