package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameService {
    Game getGameById(int gameId);
    Game findGameByName(String gameName);
    Player registerPlayer(String playerName);
    Player removePlayer(int playerId);
    Game registerPlayerOnTheGame(int gameId, int playerId);
    Game unregisterPlayerFromTheGame(int gameId, int playerId);
    boolean gameExists(String gameName);
}
