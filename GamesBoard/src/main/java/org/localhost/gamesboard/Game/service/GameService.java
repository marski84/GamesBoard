package org.localhost.gamesboard.Game.service;

import org.localhost.gamesboard.Game.model.Game;

import java.util.List;

//handles basic CRUD operation
public interface GameService {
    Game getGameById(int gameId);

    Game getGameByName(String gameName);

    Game createGame(Game game);

    Game updateGame(Game game);

    Game deleteGame(int gameId);

    List<Game> getAllGames();
}
