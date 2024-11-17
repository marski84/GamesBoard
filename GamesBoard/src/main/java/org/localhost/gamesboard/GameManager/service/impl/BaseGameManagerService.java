package org.localhost.gamesboard.GameManager.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.repository.GameRepository;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.GameManager.service.GameManagerService;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.exceptions.messages.GameErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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

        ZonedDateTime gameStartTime = ZonedDateTime.now();
        //constrain/uniq
        if (game.getGameFinishDate() != null) {
            log.error("Game already finished: {}", game.getGameFinishDate());
            throw new GameStateException(GameErrorCode.GAME_ALREADY_FINISHED);
        }


        if (game.getGameStartDate() != null) {
            log.error("Game already started");
            throw new GameStateException(GameErrorCode.GAME_ALREADY_STARTED);
        }

        if (gameStartTime.isBefore(game.getCreatedAt())) {
            log.error("Game start date cannot be before creation date");
            throw new GameStateException(GameErrorCode.INVALID_GAME_START_DATE);
        }


        game.setGameStartDate(gameStartTime);
        return gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
    public Game endGame(int gameId) {
        Game game = getGameById(gameId);
        if (game.getGameStartDate() == null) {
            log.error("Game not started");
            throw new GameStateException(GameErrorCode.GAME_NOT_STARTED);
        }

        if (game.getGameFinishDate() != null) {
            log.error("Game already ended");
            throw new GameStateException(GameErrorCode.GAME_ALREADY_FINISHED);
        }

        ZonedDateTime gameFinishTime = ZonedDateTime.now();
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
                    return new GameStateException(GameErrorCode.GAME_NOT_FOUND);
                }
        );
    }


}
