package org.localhost.gamesboard.GameManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseGameManagerFacadeTest {

    private BaseGameManagerFacade objectUnderTest;
    private Game testGame;
    private final String TEST_GAME_NAME = "Test Game Name";
    private final int TEST_GAME_ID = 1;
    private final String MOCK_SCORE_NAME = "Mock Score Name";
    private final int MOCK_SCORE = 11111;
    private final int MOCK_PLAYER_ID = 123;

    @Mock
    private GameManagerService gameManagerService;

    @BeforeEach
    void setUp() {
        objectUnderTest = new BaseGameManagerFacade(gameManagerService);
        testGame = new Game();
        testGame.setGameName(TEST_GAME_NAME);
        testGame.setId(TEST_GAME_ID);
    }

    @Test
    void startGame() {
//        given
        when((gameManagerService.startGame(testGame.getId()))).thenReturn(testGame);
//        when
        objectUnderTest.startGame(testGame.getId());
//        then
        verify(gameManagerService).startGame(testGame.getId());
    }

    @Test
    void endGame() {
//        given
        when(gameManagerService.endGame(testGame.getId())).thenReturn(testGame);
//        when
        objectUnderTest.endGame(testGame.getId());
//        then
        verify(gameManagerService).endGame(testGame.getId());
    }

    @Test
    void saveGameScore() {
//        given
        List<PlayerScore> playerScores = new ArrayList<>();
        PlayerScore playerScore = new PlayerScore();
        playerScore.setScore(MOCK_SCORE);
        playerScore.setPlayerName(MOCK_SCORE_NAME);
        playerScores.add(playerScore);

        when(gameManagerService.saveGameScore(testGame.getId(), playerScores)).thenReturn(testGame);
//        when
        objectUnderTest.saveGameScore(testGame.getId(), playerScores);
//        then
        verify(gameManagerService).saveGameScore(testGame.getId(), playerScores);
    }

    @Test
    void isGameActive() {
//        given
        when(gameManagerService.isGameActive(testGame.getId())).thenReturn(true);
//        when
        objectUnderTest.isGameActive(testGame.getId());
//        then
        verify(gameManagerService).isGameActive(testGame.getId());
    }


    @Test
    void getPlayersRegisteredInGame() {
//        given
        when(gameManagerService.getPlayersInGame(testGame.getId())).thenReturn(new ArrayList<>());
//        when
        objectUnderTest.getPlayersRegisteredInGame(testGame.getId());
//        then
        verify(gameManagerService).getPlayersInGame(testGame.getId());
    }

    @Test
    void isPlayerRegisteredInGame() {
//        given
        when(gameManagerService.isPlayerInGame(testGame.getId(), MOCK_PLAYER_ID)).thenReturn(true);
//        when
        objectUnderTest.isPlayerRegisteredInGame(testGame.getId(), MOCK_PLAYER_ID);
//        then
        verify(gameManagerService).isPlayerInGame(testGame.getId(), MOCK_PLAYER_ID);
    }
}