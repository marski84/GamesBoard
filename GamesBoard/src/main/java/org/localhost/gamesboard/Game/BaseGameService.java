package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.exceptions.*;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.Player.PlayerRepository;
import org.localhost.gamesboard.Player.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseGameService implements GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public BaseGameService(GameRepository gameRepository, PlayerRepository playerRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    public boolean gameExists(String gameName) {
        return gameRepository.existsGameByGameName(gameName);
    }


    public Game getGameById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                () -> new GameNotFoundException("Game not found")
        );
    }

    public Game findGameByName(String gameName) {
        return gameRepository.findByGameName(gameName).orElseThrow(
                () -> new GameNotFoundException("Game not found")
        );
    }

    @Override
    public Player registerPlayer(String playerName) {
        try {
            return playerService.registerPlayer(playerName);
        } catch (Exception e) {
            throw new PlayerNotFoundException("Player not found");
        }
    }

    public Player removePlayer(int playerId) {
        try {
            return playerService.removePlayer(playerId);
        } catch (PlayerNotFoundException e) {
            throw new PlayerNotFoundException("Player not found");
        }
    }

    @Override
    @Transactional
    public Game registerPlayerOnTheGame(int gameId, int playerId) {
        if (gameId < 0 || playerId < 0) {
            throw new IllegalArgumentException("Game id or player id cannot be negative");
        }
        Game game = getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);

        game.addPlayer(player);
        player.setGame(game);
        gameRepository.save(game);
        playerRepository.save(player);
        return game;
    }

    @Override
    @Transactional
    public Game unregisterPlayerFromTheGame(int gameId, int playerId) {
        Game game = getGameById(gameId);
        Player player = playerService.getPlayerData(playerId);
        game.removePlayer(player);
        gameRepository.save(game);
        return game;
    }


}
