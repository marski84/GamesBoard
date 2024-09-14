package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.localhost.gamesboard.model.Game;

public interface AggregateFacade {
    GameWithPlayersDto addPlayerToGame(int gameId, int playerId);
    GameWithPlayersDto removePlayerFromGame(int gameId, int playerId);
}
