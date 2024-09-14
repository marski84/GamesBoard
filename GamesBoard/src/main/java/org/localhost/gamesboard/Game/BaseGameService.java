package org.localhost.gamesboard.Game;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.localhost.gamesboard.exceptions.*;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.Player.PlayerRepository;
import org.localhost.gamesboard.Player.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                }        );
    }

    @Override
    public Game updateGame(Game game) {
        if (game == null || game.getGameName() == null) {
            log.error("Game name is null or empty");
            throw new IllegalArgumentException("Game name cannot be null");
        }
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(int gameId) {

    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        return games;
    }

     public void validateGame(Game game) {
        if (game == null || game.getGameName() == null) {
            log.error("Game name is null or empty");
            throw new IllegalArgumentException("Game name cannot be null");
        }
    }


}
