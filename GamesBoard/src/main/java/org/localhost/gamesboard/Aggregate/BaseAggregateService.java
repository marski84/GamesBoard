package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.GameManager.GameManagerService;
import org.localhost.gamesboard.Player.PlayerService;
import org.localhost.gamesboard.Player.model.Player;
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

    @Transactional
    public Game registerPlayerOnTheGame(int gameId, int playerId) {
        Game game = gameService.getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);

        validateGameAndPlayer(game, player);

        game.addPlayer(player);
        System.out.println(game.getPlayers());
//        game = gameService.updateGame(game);
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
            throw new GameStateException(GameErrorCode.GAME_NOT_FOUND);
        }
        if (!gameManagerService.isGameActive(game.getId())) {
            throw new GameStateException(GameErrorCode.GAME_NOT_ACTIVE);
        }

        if (player == null) {
            throw new PlayerException(PlayerErrorCode.PLAYER_NOT_FOUND);
        }

//        if (gameManagerService.isPlayerInGame(game.getId(), player.getId())) {
//            throw new PlayerException(PlayerErrorCode.PLAYER_ALREADY_IN_GAME);
//        }
    }


}
