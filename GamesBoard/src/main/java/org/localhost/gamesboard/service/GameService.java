package org.localhost.gamesboard.service;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;

public interface GameService {
    Game registerNewGame(Game game);
    void startGame(int gameId);
    void endGame(int gameId);
    Game getGameById(int gameId);
    Game findGameByName(String gameName);
    Player registerPlayer(Player player);
}
