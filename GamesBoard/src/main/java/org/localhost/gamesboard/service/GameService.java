package org.localhost.gamesboard.service;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameService {
    Game registerNewGame(Game game);
    Game startGame(int gameId);
    Game endGame(int gameId);
    Game getGameById(int gameId);
    Game findGameByName(String gameName);
    Player registerPlayer(Player player);
    Player removePlayer(int playerId);
    Game registerPlayerOnTheGame(int gameId, int playerId);
    Game unregisterPlayerFromTheGame(int gameId, int playerId);
    Game saveGameScore(int gameId, List<PlayerScore> gameScore);
}
