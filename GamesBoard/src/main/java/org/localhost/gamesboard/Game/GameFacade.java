package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameFacade {
    GameWithPlayersDto addPlayerToGame(int gameId, int playerId);
    GameWithPlayersDto removePlayerFromGame(int gameId, int playerId);
    GameDataDto getGameByName(String gameName);
    GameDataDto getGameById(int gameId);

}
