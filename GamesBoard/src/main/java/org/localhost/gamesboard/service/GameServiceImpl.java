package org.localhost.gamesboard.service;

import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
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

    public Game registerNewGame(Game newGame) {
        if (newGame == null || newGame.getGameName() == null) {
            throw new IllegalArgumentException("Game or game name cannot be null");
        }        return gameRepository.save(newGame);
    }

    @Transactional
    public void startGame(int gameId) {
        Game game = getGameById(gameId);
        LocalDateTime gameStartTime = LocalDateTime.now();
        game.setGameStartDate(gameStartTime);
        gameRepository.save(game);
    }

    @Transactional
    public void endGame(int gameId) {
        Game game = getGameById(gameId);
        LocalDateTime gameStartTime = LocalDateTime.now();
        game.setGameFinishDate(gameStartTime);
        gameRepository.save(game);
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
}
