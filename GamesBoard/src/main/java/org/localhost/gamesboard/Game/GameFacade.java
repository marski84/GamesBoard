package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.GameDataDto;

public interface GameFacade {

    GameDataDto getGameByName(String gameName);

    GameDataDto getGameById(int gameId);

}
