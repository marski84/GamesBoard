package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Game.model.Game;

import java.util.List;


public interface GameHandler {

    GameDtoBuilder getGameByName(String gameName);

    GameDtoBuilder getGameById(int gameId);

    GameDtoBuilder updateGame(Game game);

    GameDtoBuilder createGame(String gameName);

    void deleteGame(int gameId);

    List<GameDtoBuilder> getAllGames();
}
