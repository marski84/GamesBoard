package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;

public interface GameFacade {
    Game createGame(String gameName);
    Game startGame(int gameId);
    void endGame(int gameId);
    Player addPlayer(String playerName);
    Player removePlayer(int playerId);
    void saveGameScore(int gameId, String gameScore);
    Game getGameByName(String gameName);
    Game getGameById(int gameId);

}
