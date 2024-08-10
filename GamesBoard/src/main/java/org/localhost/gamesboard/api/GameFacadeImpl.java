package org.localhost.gamesboard.api;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.GameFacade;
import org.localhost.gamesboard.service.GameService;

import java.util.Objects;

public class GameFacadeImpl implements GameFacade {

    private final GameService gameService;

    public GameFacadeImpl(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void createGame(String gameName) {
        Objects.requireNonNull(gameName);
        Game newGame = new Game();
        newGame.setGameName(gameName);
        gameService.registerNewGame(newGame);

    }

    @Override
    public void startGame(int gameId) {

    }

    @Override
    public void endGame(int gameId) {

    }

    @Override
    public void addPlayer(String playerName) {

    }

    @Override
    public void removePlayer(String playerName) {

    }

    @Override
    public void saveGameScore(int gameId, String gameScore) {

    }

    @Override
    public int getGameId(String gameName) {
        return 0;
    }

    @Override
    public Game getGameById(int gameId) {
        return null;
    }
}
