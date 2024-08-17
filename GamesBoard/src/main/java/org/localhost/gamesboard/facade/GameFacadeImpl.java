package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.service.GameServiceImpl;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class GameFacadeImpl implements GameFacade {

    private final GameServiceImpl gameServiceImpl;


    public GameFacadeImpl(GameServiceImpl gameServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
    }

//    @GetMapping("/links/{linkId}")

    @Override

    public Game createGame(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        Game game = new Game();
        game.setGameName(gameName);
        return gameServiceImpl.registerNewGame(game);
    }

    @Override
    public Game startGame(int gameId) {
        Game game = gameServiceImpl.startGame(gameId);
        return game;
    }

    @Override
    public void endGame(int gameId) {
        gameServiceImpl.endGame(gameId);
    }

    @Override
    public Player addPlayer(String playerName) {
        if (playerName == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        Player player = new Player();
        player.setPlayerNickname(playerName);
        return gameServiceImpl.registerPlayer(player);

    }

    @Override
    public Player removePlayer(int playerId) {
        return gameServiceImpl.removePlayer(playerId);
    }

    @Override
    public void saveGameScore(int gameId, String gameScore) {

    }

    @Override
    public Game getGameByName(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        return gameServiceImpl.findGameByName(gameName);
    }

    @Override
    public Game getGameById(int gameId) {
        return gameServiceImpl.getGameById(gameId);
    }


}
