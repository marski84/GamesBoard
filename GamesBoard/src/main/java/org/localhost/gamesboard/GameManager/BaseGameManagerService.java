package org.localhost.gamesboard.GameManager;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.exceptions.GameAlreadyFinishedException;
import org.localhost.gamesboard.exceptions.GameAlreadyStartedException;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.exceptions.GameNotStartedException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BaseGameManagerService implements GameManagerService {
    private final GameRepository gameRepository;


    public BaseGameManagerService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    public Game startGame(int gameId) {
        Game game = getGameById(gameId);
        if (game.getGameStartDate() != null) {
            log.error("Game already started");
            throw new GameAlreadyStartedException("Game already started");
        }
        LocalDateTime gameStartTime = LocalDateTime.now();
        game.setGameStartDate(gameStartTime);
        return gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
    public Game endGame(int gameId) {
        Game game = getGameById(gameId);
        if (game.getGameStartDate() == null) {
            log.error("Game not started");
            throw new GameNotStartedException("Game not started");
        }

        if (game.getGameFinishDate() != null) {
            log.error("Game already ended");
            throw new GameAlreadyFinishedException("Game already finished");
        }

        LocalDateTime gameFinishTime = LocalDateTime.now();
        game.setGameFinishDate(gameFinishTime);
        return gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
    public Game saveGameScore(int gameId, List<PlayerScore> gameScore) {
        Game game = getGameById(gameId);
        game.setGameScore(gameScore);
        return gameRepository.save(game);
    }

    @Override
    public boolean isGameActive(int gameId) {
        Game game = getGameById(gameId);
        return game.getGameStartDate() != null && game.getGameFinishDate() == null;
    }

    @Override
    public List<Player> getPlayersInGame(int gameId) {
        Game game = getGameById(gameId);
        return game.getPlayers();
    }

    @Override
    public boolean isPlayerInGame(int gameId, int playerId) {
        Game game = getGameById(gameId);
        if (game.getPlayers() != null) {
            return game.getPlayers().stream().anyMatch(player -> player.getId() == playerId);
        } else {
            return false;
        }

    }

    public Game getGameById(int gameId) {
        if (gameId < 0) {
            throw new IllegalArgumentException("Game id cannot be negative");
        }
        return gameRepository.findById(gameId).orElseThrow(
                () -> {
                    log.error("Game not found");
                    return new GameNotFoundException("Game not found");
                }
        );
    }


}
