package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.localhost.gamesboard.service.GameService;
import org.localhost.gamesboard.service.PlayerServiceImpl;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class GameFacadeImpl implements GameFacade {

    private final GameService gameServiceImpl;
    private final PlayerServiceImpl playerServiceImpl;


    public GameFacadeImpl(GameService gameServiceImpl, PlayerServiceImpl playerServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
        this.playerServiceImpl = playerServiceImpl;
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
    public Game addPlayerToGame(int gameId, int playerId) {
        return gameServiceImpl.registerPlayerOnTheGame(gameId, playerId);
    }

    @Override
    public Game removePlayerFromGame(int gameId, int playerId) {
        return gameServiceImpl.unregisterPlayerFromTheGame(gameId, playerId);
    }

    @Override
    public Game endGame(int gameId) {
        Game game = gameServiceImpl.endGame(gameId);
        return game;
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
    public Game saveGameScore(int gameId, List<PlayerScore> gameScore) {
        return gameServiceImpl.saveGameScore(gameId, gameScore);
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
