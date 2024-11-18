package org.localhost.gamesboard.GameManager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.repository.GameRepository;
import org.localhost.gamesboard.Game.service.GameService;
import org.localhost.gamesboard.Game.service.impl.BaseGameService;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.GameManager.service.impl.BaseGameManagerService;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.repository.InMemoryGameRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseGameManagerServiceTest {
    GameManagerService objectUnderTest;
    GameService baseGameService;
    GameRepository gameRepository;
    private Game testGame;
    private final String TEST_GAME_NAME = "Test game";

    @BeforeEach
    void setUp() {
        gameRepository = new InMemoryGameRepository();
        objectUnderTest = new BaseGameManagerService(gameRepository);
        baseGameService = new BaseGameService(gameRepository);
        testGame = new Game();

    }

    @Test
    @DisplayName("startGame should successfully start a game")
    void startGame() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
//        when
        objectUnderTest.startGame(savedGame.getId());
        Game testResult = baseGameService.getGameById(savedGame.getId());
//        then
        assertNotNull(testResult.getGameStartDate());
    }

    @Test
    @DisplayName("startGame should throw when game is already started")
    void startGameAlreadyStarted() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
//        when
        objectUnderTest.startGame(savedGame.getId());
        Game testResult = baseGameService.getGameById(savedGame.getId());
//        then
        assertThrows(
                GameStateException.class,
                () -> objectUnderTest.startGame(testResult.getId())
        );
    }

    @Test
    @DisplayName("endGame should successfully finish previously started game")
    void endGame() {
//        given, when
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        objectUnderTest.startGame(savedGame.getId());
        objectUnderTest.endGame(savedGame.getId());
        Game testResult = baseGameService.getGameById(savedGame.getId());
        ZonedDateTime gameEndDate = ZonedDateTime.now();
//        then
        assertEquals(gameEndDate.getHour(), testResult.getGameFinishDate().getHour());
    }

    @Test
    @DisplayName("endGame should throw when game is not started")
    void endGameNotStarted() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
//       when, then
        assertThrows(
                GameStateException.class,
                () -> objectUnderTest.endGame(savedGame.getId())
        );
    }

    @Test
    @DisplayName("endGame should throw when game is already finished")
    void endGameAlreadyFinished() {
//    given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
//    when, then
        assertThrows(
                GameStateException.class,
                () -> objectUnderTest.endGame(savedGame.getId())
        );
    }

    @Test
    @DisplayName("saveGameScore should successfully save game score")
    void saveGameScore() {
//        given
        List<PlayerScore> scores = new ArrayList<>();
        PlayerScore firstPlayerScore = new PlayerScore();
        PlayerScore secondPlayerScore = new PlayerScore();

        firstPlayerScore.setScore(10);
        firstPlayerScore.setPlayerName("test player");
        secondPlayerScore.setScore(40);
        secondPlayerScore.setPlayerName("test player2");
        scores.add(firstPlayerScore);
        scores.add(secondPlayerScore);
//        when
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        objectUnderTest.saveGameScore(savedGame.getId(), scores);
        Game testResult = baseGameService.getGameById(savedGame.getId());
//        then
        assertEquals(testResult.getGameScore(), savedGame.getGameScore());
    }

    @Test
    @DisplayName("isGameActive should return true when game is active")
    void isGameActive() {
//        when
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        objectUnderTest.startGame(savedGame.getId());
        boolean testResult = objectUnderTest.isGameActive(savedGame.getId());
//        then
        assertTrue(testResult);
    }

    @Test
    @DisplayName("isGameActive should return false if game is finished")
    void isGameFinished() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        objectUnderTest.startGame(savedGame.getId());
        objectUnderTest.endGame(savedGame.getId());
//        when
        boolean testResult = objectUnderTest.isGameActive(savedGame.getId());
//        then
        assertFalse(testResult);
    }

    @Test
    @DisplayName("getPlayersInGame should return all players in the game")
    void getPlayersInGame() {
//        given
        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setPlayerNickname("player1");
        player2.setPlayerNickname("player2");
        players.add(player1);
        players.add(player2);


        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        savedGame.setPlayers(players);
        baseGameService.updateGame(savedGame);
//        when
        objectUnderTest.getPlayersInGame(savedGame.getId());
//        then
        assertEquals(players, objectUnderTest.getPlayersInGame(savedGame.getId()));
    }

    @Test
    @DisplayName("isPlayerInGame should return true when player is in the game")
    void isPlayerInGame() {
//        given
        Player player1 = new Player();
        player1.setId(1);
        player1.setPlayerNickname("player1");

        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
        savedGame.setPlayers(List.of(player1));
        baseGameService.updateGame(savedGame);
        //        when
        boolean testResult = objectUnderTest.isPlayerInGame(savedGame.getId(), player1.getId());
//        then
        assertTrue(testResult);
    }

    @Test
    @DisplayName("isPlayerInGame should return false when no player in the game")
    void isPlayerInGameNoPlayer() {
//        given
        Player player1 = new Player();
        player1.setId(1);

        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = baseGameService.createGame(testGame);
//        when
        boolean testResult = objectUnderTest.isPlayerInGame(savedGame.getId(), player1.getId());
//        then
        assertFalse(testResult);
    }
}