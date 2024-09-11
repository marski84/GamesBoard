package org.localhost.gamesboard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.exceptions.*;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.localhost.gamesboard.repository.InMemoryGameRepository;
import org.localhost.gamesboard.repository.InMemoryPlayerRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private final int NOT_EXISTING_GAME_ID = 10;


    private InMemoryGameRepository gameRepository;
    private InMemoryPlayerRepository playerRepository;
    private PlayerServiceImpl playerService;

    private GameServiceImpl objectUnderTest;

    private Game testGame;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        testGame = new Game();
        testGame.setId(1);
        testGame.setGameName("Test game");

        testPlayer = new Player();
        testPlayer.setPlayerNickname("Test player");

        gameRepository = new InMemoryGameRepository();
        playerRepository = new InMemoryPlayerRepository();
        playerService = new PlayerServiceImpl(playerRepository);
        objectUnderTest = new GameServiceImpl(gameRepository, playerRepository, playerService);
    }

    @Test
    @DisplayName("registerNewGame should create a new game")
    void registerNewGame() {
//       given, when
        Game testResult = objectUnderTest.registerNewGame(testGame);
//        then
        System.out.println(testResult);
        assertEquals(testGame.getId(), gameRepository.findById(testResult.getId()).get().getId());

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
        objectUnderTest.registerNewGame(testGame);
//        when
        objectUnderTest.startGame(testGame.getId());
       Game testResult = gameRepository.findById(testGame.getId()).get();
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
        gameRepository.save(testGame);
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
        gameRepository.save(testGame);
//        when
        objectUnderTest.endGame(testGame.getId());
        Game testResult = gameRepository.findById(testGame.getId()).get();
//        then
        assertEquals(testGame.getId(), testResult.getId());
        assertNotNull(testResult.getGameFinishDate());
        assertEquals(testGame.getGameName(), testResult.getGameName());
    }

    @Test
    @DisplayName("endGame should throw when trying to finish not started game")
    void endGameShouldThrowWhenTryingToFinishNotStartedGame() {
//        given,
        gameRepository.save(testGame);
//        when, then
        assertThrows(GameNotStartedException.class, () -> objectUnderTest.endGame(testGame.getId()));
    }


    @Test
    @DisplayName("endGame should throw when trying to finish already started game")
    void endGameShouldThrowWhenTryingToFinishAlreadyFinishedGame() {
//        given, when,
        testGame.setGameStartDate(LocalDateTime.now());
        testGame.setGameFinishDate(LocalDateTime.now());
        gameRepository.save(testGame);
        assertThrows(GameAlreadyFinishedException.class, () -> objectUnderTest.endGame(testGame.getId()));
    }

    @Test
    @DisplayName("getGameById should return game")
    void getGameById() {
//        given
    gameRepository.save(testGame);
//        when
 Game testResult = objectUnderTest.getGameById(testGame.getId());
//        then
        assertEquals(testGame.getId(), testResult.getId());
        assertNotNull(testResult);
    }

    @Test
    @DisplayName("getGameById should throw when game not present")
    void getGameByIdShouldThrowWhenGameNotPresent() {
        assertThrows(GameNotFoundException.class, () -> objectUnderTest.getGameById(NOT_EXISTING_GAME_ID));
    }

    @Test
    @DisplayName("findGameByName should return game")
    void findGameByName() {
//        given
        gameRepository.save(testGame);
//        when
        Game testResult = objectUnderTest.findGameByName(testGame.getGameName());
//        then
        assertEquals(testGame.getGameName(), testResult.getGameName());
        assertNotNull(testResult);
    }

    @Test
    @DisplayName("findGameByName should throw when no game present")
    void findGameByNameShouldThrowWhenNoGamePresent() {
        assertThrows(GameNotFoundException.class, () -> objectUnderTest.findGameByName(""));
    }
    @Test
    @DisplayName("registerPlayer should return player")
    void registerPlayer() {
//        when
        Player testResult = objectUnderTest.registerPlayer(testPlayer.getPlayerNickname());
//        then
        assertEquals(testPlayer.getPlayerNickname(), testResult.getPlayerNickname());
        assertNotNull(testResult);
    }

    @Test
    @DisplayName("registerPlayer should throw when plater is null")
    void registerPlayerShouldThrowWhenPlayerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerPlayer(null));
    }

    @Test
    @DisplayName("remove player should successfull remove player")
    void removePlayer() {
//        given
        objectUnderTest.registerPlayer(testPlayer.getPlayerNickname());
//        when
        Player testResult = objectUnderTest.removePlayer(testPlayer.getId());
//        then
        assertEquals(testPlayer.getPlayerNickname(), testResult.getPlayerNickname());
        assertNotNull(testResult);
    }

    @Test
    @DisplayName("removePlayer should throw when player not found")
    void removePlayerShouldThrowWhenPlayerNotFound() {
        assertThrows(PlayerNotFoundException.class, () -> objectUnderTest.removePlayer(NOT_EXISTING_GAME_ID));
    }

    @Test
    @DisplayName("registerPlayerOnTheGame should add player to the game")
    void registerPlayerOnTheGame() {
//        given
        objectUnderTest.registerPlayer(testPlayer.getPlayerNickname());
        objectUnderTest.registerNewGame(testGame);
//        when
        Game testResult = objectUnderTest.registerPlayerOnTheGame(testGame.getId(), testPlayer.getId());
//        then
        assertNotNull(testResult.getPlayers());
        assertEquals(testPlayer.getPlayerNickname(), testResult.getPlayers().get(0).getPlayerNickname());
    }

    @Test
    @DisplayName("registerPlayerOnTheGame should throw when given a negative gameId")
    void registerPlayerOnTheGameShouldThrowWhenGameIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerPlayerOnTheGame(-1, 2));
    }

    @Test
    @DisplayName("registerPlayerOnTheGame should throw when given a negative playerId")
    void registerPlayerOnTheGameShouldThrowWhenPlayerIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> objectUnderTest.registerPlayerOnTheGame(1, -2));
    }

    @Test
    @DisplayName("unregisterPlayerFromTheGame should successfully unregister player")
    void unregisterPlayerFromTheGame() {
//        given
        Game gameWithPlayers = new Game();
        Player player = objectUnderTest.registerPlayer(testPlayer.getPlayerNickname());
        List<Player> playersList = new ArrayList<>();
        playersList.add(player);
        gameWithPlayers.setPlayers(playersList);
        gameWithPlayers.setGameName("Game with player");
        gameWithPlayers.setId(0);

        objectUnderTest.registerNewGame(gameWithPlayers);
//        when
        Game testResult = objectUnderTest.unregisterPlayerFromTheGame(gameWithPlayers.getId(), testPlayer.getId());
//        then
        assertTrue(testResult.getPlayers().isEmpty());
    }

    @Test
    @DisplayName("unregisterPlayerFromTheGame should throw when player does not exist")
    void unregisterPlayerFromTheGameShouldThrowWhenPlayerDoesNotExist() {
//        given
        objectUnderTest.registerNewGame(testGame);
//        when, then
        assertThrows(
                PlayerNotFoundException.class,
                () -> objectUnderTest.unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId())
        );
    }

    @Test
    @DisplayName("saveGameScore sould update gamescore")
    void saveGameScore() {
//        given
        PlayerScore firstPlayerScore = new PlayerScore();
        PlayerScore secondPlayerScore = new PlayerScore();
        firstPlayerScore.setScore(200);
        firstPlayerScore.setPlayerName("strucel");
        secondPlayerScore.setScore(300);
        secondPlayerScore.setPlayerName("wentyl");
        List<PlayerScore> scores = Arrays.asList(firstPlayerScore, secondPlayerScore);

        objectUnderTest.registerNewGame(testGame);
//        when
        Game testResult = objectUnderTest.saveGameScore(testGame.getId(), scores);
//        then
        assertEquals(testGame.getId(), testResult.getId());
        assertFalse(testResult.getPlayersScores().isEmpty());
    }




}