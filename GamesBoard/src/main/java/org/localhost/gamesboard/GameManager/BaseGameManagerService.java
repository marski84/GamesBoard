package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Game.GameRepository;
import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.exceptions.GameAlreadyFinishedException;
import org.localhost.gamesboard.exceptions.GameAlreadyStartedException;
import org.localhost.gamesboard.exceptions.GameNotFoundException;
import org.localhost.gamesboard.exceptions.GameNotStartedException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BaseGameManagerService implements GameManagerService {
    private final GameRepository gameRepository;


    public BaseGameManagerService(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
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
        return gameRepository.save(game);
    }

    @Transactional(rollbackFor = Exception.class)
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
        return gameRepository.save(game);
    }

    @Transactional
    public Game saveGameScore(int gameId, List<PlayerScore> gameScore) {
        Game game = getGameById(gameId);
        game.setPlayersScores(gameScore);
        return gameRepository.save(game);
    }

    @Override
    public Game getGameById(int gameId) {
            return gameRepository.findById(gameId).orElseThrow(
                () -> new GameNotFoundException("Game not found")
            );
    }


}
