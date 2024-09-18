package org.localhost.gamesboard.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.model.Game;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseGameFacadeTest {
    private BaseGameFacade objectUnderTest;

    @Mock
    private GameService gameService;

    private Game game;
    private final String TEST_GAME_NAME = "testGame";
    private final int MOCK_GAME_ID = 1;

    @BeforeEach
    void setUp() {
        objectUnderTest = new BaseGameFacade(gameService);

        game = new Game();
        game.setGameName(TEST_GAME_NAME);
    }

    @Test
    void getGameByName() {
//        given
        when(gameService.getGameByName(game.getGameName())).thenReturn(game);
//        when
        objectUnderTest.getGameByName(game.getGameName());
//        then
        verify(gameService).getGameByName(game.getGameName());
    }

    @Test
    void getGameById() {
        //        given
        game.setId(MOCK_GAME_ID);
        when(gameService.getGameById(game.getId())).thenReturn(game);
//        when
        objectUnderTest.getGameById(game.getId());
//        then
        verify(gameService).getGameById(game.getId());
    }

    @Test
    void updateGame() {
//        given
        when(gameService.updateGame(game)).thenReturn(game);
//        when
        game.setGameName("testGameModified");
        game.setGameStartDate(LocalDateTime.now());
        objectUnderTest.updateGame(game);
//        then
        verify(gameService).updateGame(game);
    }

    @Test
    void createGame() {
        //        given
        when(gameService.createGame(any(Game.class))).thenReturn(game);


//        when
        game.setId(null);
        objectUnderTest.createGame(TEST_GAME_NAME);
//        then
        verify(gameService).createGame(argThat(game ->
                game.getGameName().equals(TEST_GAME_NAME) && game.getId() == null
        ));
    }

    @Test
    void deleteGame() {
//        given
        game.setId(MOCK_GAME_ID);
        when(gameService.deleteGame(game.getId())).thenReturn(game);
//        when
        objectUnderTest.deleteGame(game.getId());
//        then
        verify(gameService).deleteGame(game.getId());
    }

    @Test
    void getAllGames() {
//        given
        game.setId(MOCK_GAME_ID);
        Game secondGame = new Game();
        secondGame.setId(MOCK_GAME_ID + 1);
        secondGame.setGameName("secondGame");
        List<Game> games = new ArrayList<>();
        games.add(game);
        games.add(secondGame);
//        when
        when(gameService.getAllGames()).thenReturn(games);
        objectUnderTest.getAllGames();
//        then
        verify(gameService).getAllGames();
    }
}