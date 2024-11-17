package org.localhost.gamesboard.Game.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.repository.GameRepository;
import org.localhost.gamesboard.Game.service.GameService;
import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.exceptions.messages.GameErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BaseGameService implements GameService {
    private final GameRepository gameRepository;

    public BaseGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    public Game createGame(Game newGame) {
        Optional.ofNullable(newGame)
                .map(Game::getGameName)
                .orElseGet(() -> {
                    log.error("Game is null or game name is empty");
                    throw new IllegalArgumentException("Game and game name cannot be null");
                });

        ZonedDateTime now = ZonedDateTime.now();
        newGame.setCreatedAt(now);
        return gameRepository.save(newGame);
    }

    public Game getGameById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                () -> new GameStateException(GameErrorCode.GAME_NOT_FOUND)
        );
    }

    public Game getGameByName(String gameName) {
        return gameRepository.findByGameName(gameName).orElseThrow(
                () -> {
                    log.warn("Game with name {} not found", gameName);
                    return new GameStateException(GameErrorCode.GAME_NOT_FOUND);
                });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Game updateGame(Game game) {
        Optional.ofNullable(game)
                .orElseThrow(() -> {
                    log.error("Game is null");
                    return new IllegalArgumentException("Game cannot be null");
                });
        return gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Game deleteGame(int gameId) {
        Game game = getGameById(gameId);
        gameRepository.delete(game);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }
}
