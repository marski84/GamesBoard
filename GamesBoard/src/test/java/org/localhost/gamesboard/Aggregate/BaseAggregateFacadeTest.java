package org.localhost.gamesboard.Aggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.Player.PlayerRepository;
import org.localhost.gamesboard.Player.PlayerService;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseAggregateFacadeTest {

    @Mock
    private BaseAggregateService baseAggregateService;

    private BaseAggregateFacade objectUnderTest;
    private Game testGame;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        objectUnderTest = new BaseAggregateFacade(baseAggregateService);

        testGame = new Game();
        testGame.setGameName("game");
        testGame.setId(1);
        testGame.setCreatedAt(LocalDateTime.now());
        testGame.setGameStartDate(LocalDateTime.now());

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
        GameWithPlayersDto result = objectUnderTest.addPlayerToGame(testGame.getId(), testPlayer.getId());
        // then
        verify(baseAggregateService).registerPlayerOnTheGame(testGame.getId(), testPlayer.getId());
        assertNotNull(result);

    }

    @Test
    void removePlayerFromGame() {
//        given, when
        when(baseAggregateService.unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId())).thenReturn(testGame)
                .thenReturn(testGame);
        GameWithPlayersDto result = objectUnderTest.removePlayerFromGame(testGame.getId(), testPlayer.getId());
//       then
        verify(baseAggregateService).unregisterPlayerFromTheGame(testGame.getId(), testPlayer.getId());
    }
}