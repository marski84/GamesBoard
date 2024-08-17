package org.localhost.gamesboard.service;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;

public interface GameService {
    Game registerNewGame(Game game);
    Game startGame(int gameId);
    Game endGame(int gameId);
    Game getGameById(int gameId);
    Game findGameByName(String gameName);
    Player registerPlayer(Player player);
}
