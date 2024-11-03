package org.localhost.gamesboard.Aggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Game.BaseGameService;
import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.GameManager.BaseGameManagerService;
import org.localhost.gamesboard.GameManager.GameManagerService;
import org.localhost.gamesboard.Player.BasePlayerService;
import org.localhost.gamesboard.Player.PlayerRepository;
import org.localhost.gamesboard.Player.PlayerService;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.exceptions.PlayerException;
import org.localhost.gamesboard.repository.InMemoryGameRepository;
import org.localhost.gamesboard.repository.InMemoryPlayerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseAggregateServiceTest {

    private BaseAggregateService objectUnderTest;
    private PlayerService playerService;
    private GameService gameService;
    private GameManagerService gameManagerService;

    private final String TEST_PLAYER_NAME = "TestPlayer";

    private Game testGame;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        GameRepository gameRepository = new InMemoryGameRepository();
        playerService = new BasePlayerService(playerRepository);
        gameService = new BaseGameService(gameRepository);
        gameManagerService = new BaseGameManagerService(gameRepository);

        objectUnderTest = new BaseAggregateService(playerService, gameService, gameManagerService);

        testPlayer = new Player();

        testGame = new Game();
        testGame.setGameName("testGame");
        gameService.createGame(testGame);
        gameManagerService.startGame(testGame.getId());
    }

    @Test
    @DisplayName("registerPlayerOnTheGame should throw when game is not present")
    void registerPlayerOnTheGameWhenGameIsNotPresent() {
        Game game = new Game();
        game.setId(432452);
        assertThrows(
                GameStateException.class,
                () -> objectUnderTest.registerPlayerOnTheGame(game.getId(), testPlayer.getId())
        );
    }


    @Test
    @DisplayName("registerPlayerOnTheGame should throw when player is not present")
    void registerPlayerOnTheGameWhenPlayerIsNotPresent() {
        Player player = new Player();
        player.setId(432452);
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.registerPlayerOnTheGame(testGame.getId(), player.getId())
        );
    }

    @Test
    @DisplayName("unregisterPlayerFromTheGame should successfully unregister player from the game")
    void unregisterPlayerFromTheGame() {
        //        given
        testPlayer = playerService.registerPlayer(TEST_PLAYER_NAME);
        objectUnderTest.registerPlayerOnTheGame(testGame.getId(), testPlayer.getId());
        List<Player> expectedPlayers = new ArrayList<>(testGame.getPlayers());
//        when,
        Game testResult = objectUnderTest.unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId());
//        then
        assertEquals(testGame.getGameName(), testResult.getGameName());
        assertNotEquals(expectedPlayers, testResult.getPlayers());
    }

    @Test
    @DisplayName("unregisterPlayerOnTheGame should throw when game is not present")
    void unregisterPlayerOnTheGameWhenGameIsNotPresent() {
        Game game = new Game();
        game.setId(432452);
        assertThrows(
                GameStateException.class,
                () -> objectUnderTest.unregisterPlayerFromTheGame(game.getId(), testPlayer.getId())
        );
    }

    @Test
    @DisplayName("unregisterPlayerOnTheGame should throw when game is not present")
    void unregisterPlayerOnTheGameWhenPlayerIsNotPresent() {
        Player player = new Player();
        player.setId(432452);
        assertThrows(
                PlayerException.class,
                () -> objectUnderTest.unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId())
        );
    }
}