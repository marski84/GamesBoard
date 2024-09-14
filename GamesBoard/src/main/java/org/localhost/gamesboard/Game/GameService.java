package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.model.Game;

import java.util.List;

//handles basic CRUD operation
public interface GameService {
    Game getGameById(int gameId);

    Game getGameByName(String gameName);

    Game createGame(Game game);

    Game updateGame(Game game);

    void deleteGame(int gameId);

    List<Game> getAllGames();

    void validateGame(Game game);
}