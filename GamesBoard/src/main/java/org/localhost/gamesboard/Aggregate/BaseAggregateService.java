package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.GameManager.GameManagerService;
import org.localhost.gamesboard.Player.PlayerService;
import org.localhost.gamesboard.exceptions.GameNotActiveException;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.exceptions.PlayerNotRegisteredInGameException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
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

    @Transactional
    public Game registerPlayerOnTheGame(int gameId, int playerId) {
        Game game = gameService.getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);
        validateGameAndPlayer(game, player);

        game.addPlayer(player);
        game = gameService.updateGame(game);
        return game;
    }

    @Transactional
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
            throw new GameNotFoundException("Game not found");
        }
        if (!gameManagerService.isGameActive(game.getId())) {
            throw new GameNotActiveException();
        }

        if (player == null) {
            throw new PlayerNotFoundException("Player not found");
        }

        if (gameManagerService.isPlayerInGame(game.getId(), player.getId())) {
            throw new PlayerNotRegisteredInGameException();
        }
    }


}
