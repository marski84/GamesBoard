package org.localhost.gamesboard.Game;

import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.model.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BaseGameService implements GameService {
    private final GameRepository gameRepository;

    public BaseGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Transactional
    public Game createGame(Game newGame) {
        if (newGame == null || newGame.getGameName() == null) {
            log.error("Game name is null or empty");
            throw new IllegalArgumentException("Game or game name cannot be null");
        }

        return gameRepository.save(newGame);
    }

    public Game getGameById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                () -> {
                    log.warn("Game with id {} not found", gameId);
                    return new GameNotFoundException("Game not found");
                }
        );
    }

    public Game getGameByName(String gameName) {
        return gameRepository.findByGameName(gameName).orElseThrow(
                () -> {
                    log.warn("Game with name {} not found", gameName);
                    return new GameNotFoundException("Game not found");
                });
    }

    @Transactional
    @Override
    public Game updateGame(Game game) {
        if (game == null || game.getGameName() == null) {
            log.error("Game name is null or empty");
            throw new IllegalArgumentException("Game name cannot be null");
        }
        return gameRepository.save(game);
    }

    @Transactional
    @Override
    public Game deleteGame(int gameId) {
        Game game = getGameById(gameId);
        gameRepository.delete(game);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();

    }

    public void validateGame(Game game) {
        if (game == null || game.getGameName() == null) {
            log.error("Game name is null or empty");
            throw new IllegalArgumentException("Game name cannot be null");
        }
    }


}
