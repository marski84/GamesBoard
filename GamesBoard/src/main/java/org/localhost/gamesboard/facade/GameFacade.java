package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;

public interface GameFacade {
    Game createGame(String gameName);
    Game startGame(int gameId);
    Game addPlayerToGame(int gameId, int playerId);
    Game removePlayerFromGame(int gameId, int playerId);
    Game endGame(int gameId);
    Player addPlayer(String playerName);
    Player removePlayer(int playerId);
    Game saveGameScore(int gameId, String gameScore);
    Game getGameByName(String gameName);
    Game getGameById(int gameId);

}
