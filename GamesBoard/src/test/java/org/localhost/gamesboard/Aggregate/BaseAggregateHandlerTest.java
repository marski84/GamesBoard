package org.localhost.gamesboard.Aggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Player.model.Player;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseAggregateHandlerTest {

    @Mock
    private BaseAggregateService baseAggregateService;

    private BaseAggregateHandler objectUnderTest;
    private Game testGame;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        objectUnderTest = new BaseAggregateHandler(baseAggregateService);

        testGame = new Game();
        testGame.setGameName("game");
        testGame.setId(1);
        testGame.setCreatedAt(Instant.now());
        testGame.setGameStartDate(Instant.now());

        testPlayer = new Player();
        testPlayer.setPlayerNickname("player");
        testPlayer.setId(1);
    }

    @Test
    void addPlayerToGame() {
//        given
        testGame.setPlayers(List.of(testPlayer));
        when(baseAggregateService.registerPlayerOnTheGame(testGame.getId(), testPlayer.getId())).thenReturn(testGame);
        // when
        GameDtoBuilder result = objectUnderTest.addPlayerToGame(testGame.getId(), testPlayer.getId());
        // then
        verify(baseAggregateService).registerPlayerOnTheGame(testGame.getId(), testPlayer.getId());
        assertNotNull(result);

    }

    @Test
    void removePlayerFromGame() {
//        given, when
        when(baseAggregateService.unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId())).thenReturn(testGame)
                .thenReturn(testGame);
        GameDtoBuilder result = objectUnderTest.removePlayerFromGame(testGame.getId(), testPlayer.getId());
//       then
        verify(baseAggregateService).unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId());
    }
}