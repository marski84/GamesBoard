package org.localhost.gamesboard.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.repository.InMemoryGameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseGameServiceTest {
    private InMemoryGameRepository gameRepository;
    private GameService objectUnderTest;
    private Game testGame;
    private final String TEST_GAME_NAME = "Test game";
    private int NON_EXISTING_GAME_ID = 100;
    private String NON_EXISTING_GAME_NAME = "Non existing game";


    @BeforeEach
    void setUp() {
        gameRepository = new InMemoryGameRepository();
        objectUnderTest = new BaseGameService(gameRepository);
        testGame = new Game();
    }


    @Test
    @DisplayName("createGame should register new game")
    void createGame() {
//        given
        Game game = new Game();
        game.setGameName(TEST_GAME_NAME);
//        when
        objectUnderTest.createGame(game);
//        then
        assertEquals(TEST_GAME_NAME, gameRepository.findByGameName(TEST_GAME_NAME).get().getGameName());

    }

    @Test
    @DisplayName("createGame should throw when game is null")
    void createGameWithNullAsGame() {
        assertThrows(
                IllegalArgumentException.class, () -> objectUnderTest.createGame(null)
        );
    }

    @Test
    @DisplayName("createGame should throw when game name is null")
    void createGameWithEmptyGameNameIsNull() {
        assertThrows(
                IllegalArgumentException.class, () -> objectUnderTest.createGame(testGame)
        );
    }

    @Test
    @DisplayName("getGameById should return game")
    void getGameById() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedTestGame = objectUnderTest.createGame(testGame);
//        when
        Game savedGameFromRepository = objectUnderTest.getGameById(savedTestGame.getId());
//      then
        assertEquals(testGame, savedGameFromRepository);
    }

    @Test
    @DisplayName("getGameById should throw when game not created")
    void getGameByIdWhenGameNotCreated() {
        assertThrows(
                GameNotFoundException.class,
                () -> objectUnderTest.getGameById(NON_EXISTING_GAME_ID)
        );
    }

    @Test
    @DisplayName("getGameByName should return previousle registered game")
    void getGameByName() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        objectUnderTest.createGame(testGame);
//        when
        Game savedTestGame = objectUnderTest.getGameByName(TEST_GAME_NAME);
//        then
        assertEquals(testGame.getGameName(), savedTestGame.getGameName());
//
    }

    @Test
    @DisplayName("getGameByName should throw when game does not exist")
    void getGameByNameWhenGameDoesNotExist() {
        assertThrows(GameNotFoundException.class, () -> objectUnderTest.getGameByName(NON_EXISTING_GAME_NAME));
    }

    @Test
    @DisplayName("updateGame should succesfully update game data")
    void updateGame() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        objectUnderTest.createGame(testGame);
//        when
        testGame.setGameName("Updated game");
        objectUnderTest.updateGame(testGame);
//        then
        assertEquals(
                testGame.getGameName(),
                gameRepository.findById(testGame.getId()).get().getGameName()
        );
    }


    @Test
    @DisplayName("updateGame should throw when game to update is null")
    void updateGameWithNullAsGame() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectUnderTest.updateGame(null)
        );
    }

    @Test
    @DisplayName("updateGame should throw when game name is null")
    void updateGameWithNullAsGameName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> objectUnderTest.updateGame(testGame)
        );
    }

    @Test
    @DisplayName("deleteGame should delete existing game")
    void deleteGame() {
//        given
        testGame.setGameName(TEST_GAME_NAME);
        Game savedGame = objectUnderTest.createGame(testGame);
//        when
        objectUnderTest.deleteGame(savedGame.getId());
//        then
        assertThrows(
                GameNotFoundException.class,
                () -> objectUnderTest.getGameById(savedGame.getId())
        );
    }

    @Test
    @DisplayName("deleteGame should throw when trying to delete non existing game")
    void deleteGameWhenGameDoesNotExist() {
        assertThrows(
                GameNotFoundException.class,
                () -> objectUnderTest.deleteGame(NON_EXISTING_GAME_ID)
        );
    }

    @Test
    @DisplayName("getAllGames should return all saved games")
    void getAllGames() {
//        given
        testGame.setGameName(TEST_GAME_NAME);

        Game secondGame = new Game();
        secondGame.setGameName("Second game");

        List<Game> addedGame = new ArrayList<>();
        addedGame.add(testGame);
        addedGame.add(secondGame);

        addedGame.forEach(game -> objectUnderTest.createGame(game));
//        when
        List<Game> savedGames = objectUnderTest.getAllGames();
//        then
        assertEquals(addedGame.size(), savedGames.size());
    }
}