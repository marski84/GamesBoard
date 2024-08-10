package org.localhost.gamesboard.service;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void registerNewGame(Game newGame) {
        Objects.requireNonNull(newGame);
        gameRepository.save(newGame);
    }
}
