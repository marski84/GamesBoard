package org.localhost.gamesboard.GameManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.exceptions.GameAlreadyFinishedException;
import org.localhost.gamesboard.exceptions.GameAlreadyStartedException;
import org.localhost.gamesboard.exceptions.GameNotStartedException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.localhost.gamesboard.repository.InMemoryGameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseGameManagerServiceTest {
    BaseGameManagerService objectUnderTest;
    GameRepository gameRepository;
    private Game testGame;
    private Game startedGame;
    private final String TEST_GAME_NAME = "Test game";
    private int NON_EXISTING_GAME_ID = 100;
    private String NON_EXISTING_GAME_NAME = "Non existing game";

    @BeforeEach
    void setUp() {
        gameRepository = new InMemoryGameRepository();
        objectUnderTest = new BaseGameManagerService(gameRepository);
        testGame = new Game();

        startedGame = new Game();
        startedGame.setGameName("startedGame");
        gameRepository.save(startedGame);
        objectUnderTest.startGame(startedGame.getId());


    }

    @Test
    @DisplayName("startGame should successfully start a game")
    void startGame() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = gameRepository.save(testGame);
//        when
        objectUnderTest.startGame(savedGame.getId());
        Game testResult = objectUnderTest.getGameById(savedGame.getId());
//        then
        assertNotNull(testResult.getGameStartDate());
    }

    @Test
    @DisplayName("startGame should throw when game is already started")
    void startGameAlreadyStarted() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = gameRepository.save(testGame);
//        when
        objectUnderTest.startGame(savedGame.getId());
        Game testResult = objectUnderTest.getGameById(savedGame.getId());
//        then
        assertThrows(
                GameAlreadyStartedException.class,
                () -> objectUnderTest.startGame(testResult.getId())
        );
    }

    @Test
    @DisplayName("endGame should successfully finish previously started game")
    void endGame() {
//        given, when
        objectUnderTest.endGame(startedGame.getId());
        Game testResult = objectUnderTest.getGameById(startedGame.getId());
//        then
        assertNotNull(testResult.getGameFinishDate());
    }

    @Test
    @DisplayName("endGame should throw when game is not started")
    void endGameNotStarted() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = gameRepository.save(testGame);
//       when, then
        assertThrows(
                GameNotStartedException.class,
                () -> objectUnderTest.endGame(savedGame.getId())
        );
    }

    @Test
    @DisplayName("endGame should throw when game is already finished")
    void endGameAlreadyFinished() {
//    given
        objectUnderTest.endGame(startedGame.getId());
//    when, then
        assertThrows(
                GameAlreadyFinishedException.class,
                () -> objectUnderTest.endGame(startedGame.getId())
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
        objectUnderTest.saveGameScore(startedGame.getId(), scores);
        Game savedGame = objectUnderTest.getGameById(startedGame.getId());
//        then
        assertNotNull(savedGame.getGameScore());
    }

    @Test
    @DisplayName("isGameActive should return true when game is active")
    void isGameActive() {
//        when
        boolean testResult = objectUnderTest.isGameActive(startedGame.getId());
//        then
        assertTrue(testResult);
    }

    @Test
    @DisplayName("isGameActive should return false if game is finished")
    void isGameFinished() {
//        given
        objectUnderTest.endGame(startedGame.getId());
//        when
        boolean testResult = objectUnderTest.isGameActive(startedGame.getId());
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
        startedGame.setPlayers(players);
        gameRepository.save(startedGame);
//        when
        objectUnderTest.getPlayersInGame(startedGame.getId());
//        then
        assertEquals(players, objectUnderTest.getPlayersInGame(startedGame.getId()));
    }

    @Test
    @DisplayName("isPlayerInGame should return true when player is in the game")
    void isPlayerInGame() {
//        given
        Player player1 = new Player();
        player1.setId(1);
        player1.setPlayerNickname("player1");
        startedGame.setPlayers(List.of(player1));
        gameRepository.save(startedGame);
//        when
       boolean testResult = objectUnderTest.isPlayerInGame(startedGame.getId(), player1.getId());
//        then
        assertTrue(testResult);
    }

    @Test
    @DisplayName("isPlayerInGame should return false when no player in the game")
    void isPlayerInGameNoPlayer() {
//        given
        Player player1 = new Player();
        player1.setId(1);
//        when
        boolean testResult = objectUnderTest.isPlayerInGame(startedGame.getId(), player1.getId());
//        then
        assertFalse(testResult);
    }
}