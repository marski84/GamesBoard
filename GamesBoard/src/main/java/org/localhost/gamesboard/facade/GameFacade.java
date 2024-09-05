package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameFacade {
    Game createGame(String gameName);
    Game startGame(int gameId);
    Game addPlayerToGame(int gameId, int playerId);
    Game removePlayerFromGame(int gameId, int playerId);
    Game endGame(int gameId);
    Player addPlayer(String playerName);
    Player removePlayer(int playerId);
    Game saveGameScore(int gameId, List<PlayerScore> gameScore);
    Game getGameByName(String gameName);
    Game getGameById(int gameId);

}
