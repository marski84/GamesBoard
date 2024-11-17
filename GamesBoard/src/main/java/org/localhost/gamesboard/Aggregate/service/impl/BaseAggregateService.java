package org.localhost.gamesboard.Aggregate.service.impl;

import org.localhost.gamesboard.Aggregate.service.AggregateService;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.service.GameService;
import org.localhost.gamesboard.GameManager.service.GameManagerService;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.Player.service.PlayerService;
import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.exceptions.PlayerException;
import org.localhost.gamesboard.exceptions.messages.GameErrorCode;
import org.localhost.gamesboard.exceptions.messages.PlayerErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseAggregateService implements AggregateService {
    private final PlayerService playerService;
    private final GameService gameService;
    private final GameManagerService gameManagerService;

    public BaseAggregateService(PlayerService playerService, GameService gameService, GameManagerService gameManagerService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.gameManagerService = gameManagerService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Game registerPlayerOnTheGame(int gameId, int playerId) {
        Game game = gameService.getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);

        validateGameAndPlayer(game, player);

        game.addPlayer(player);
        System.out.println(game.getPlayers());
        game = gameService.updateGame(game);
        return game;
    }

    @Transactional(rollbackFor = Exception.class)
    public Game unregisterPlayerFromTheGame(int gameId, int playerId) {
        Game game = gameService.getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);
        validateGameAndPlayer(game, player);

        game.removePlayer(player);
        gameService.updateGame(game);

        return game;
    }

    private void validateGameAndPlayer(Game game, Player player) {
        if (game == null) {
            throw new GameStateException(GameErrorCode.GAME_NOT_FOUND);
        }
        if (!gameManagerService.isGameActive(game.getId())) {
            throw new GameStateException(GameErrorCode.GAME_NOT_ACTIVE);
        }

        if (player == null) {
            throw new PlayerException(PlayerErrorCode.PLAYER_NOT_FOUND);
        }
    }


}
