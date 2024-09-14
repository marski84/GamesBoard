package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.model.Game;

import java.util.List;

public interface GameFacade {

    GameDataDto getGameByName(String gameName);

    GameDataDto getGameById(int gameId);

    GameDataDto updateGame(Game game);

    GameDto createGame(String gameName);

    void deleteGame(int gameId);

    List<GameDataDto> getAllGames();
}
