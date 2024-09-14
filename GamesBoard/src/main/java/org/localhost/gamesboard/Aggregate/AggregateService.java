package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.localhost.gamesboard.model.Game;

public interface AggregateService {
    Game registerPlayerOnTheGame(int gameId, int playerId);
    Game unregisterPlayerFromTheGame(int gameId, int playerId);
}
