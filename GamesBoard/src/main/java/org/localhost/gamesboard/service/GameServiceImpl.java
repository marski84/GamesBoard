package org.localhost.gamesboard.service;

import org.localhost.gamesboard.exceptions.*;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.repository.GameRepository;
import org.localhost.gamesboard.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @Transactional
    public Game registerNewGame(Game newGame) {
        if (newGame == null || newGame.getGameName() == null) {
            throw new IllegalArgumentException("Game or game name cannot be null");
        }
        return gameRepository.save(newGame);
    }

    @Transactional
    public Game startGame(int gameId) {
        Game game = getGameById(gameId);
        if (game.getGameStartDate() != null) {
            throw new GameAlreadyStartedException("Game already started");
        }
        LocalDateTime gameStartTime = LocalDateTime.now();
        game.setGameStartDate(gameStartTime);
        gameRepository.save(game);
        return gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    @Transactional
    public Game endGame(int gameId) {
        Game game = getGameById(gameId);
        if (game.getGameStartDate() == null) {
            throw new GameNotStartedException("Game not started");
        }

        if (game.getGameFinishDate() != null) {
            throw new GameAlreadyFinishedException("Game already finished");
        }

        LocalDateTime gameFinishTime = LocalDateTime.now();
        game.setGameFinishDate(gameFinishTime);
        gameRepository.save(game);
        return game;
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
    public Player registerPlayer(Player player) {
        return this.playerService.addPlayer(player);
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
        Game game = getGameById(gameId);
        Player player = playerService.getPlayerById(playerId);

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
        Player player = playerService.getPlayerById(playerId);
        game.removePlayer(player);
        gameRepository.save(game);
        return game;
    }

    @Transactional
    public Game saveGameScore(int gameId, String gameScore) {
        Game game = getGameById(gameId);
        game.setPlayersScores(gameScore);
        gameRepository.save(game);
        return game;
    }
}
