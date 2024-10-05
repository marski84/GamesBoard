package org.localhost.gamesboard.Aggregate;
import org.localhost.gamesboard.Game.dto.GameDtoBuilder;

public interface AggregateHandler {
    GameDtoBuilder addPlayerToGame(int gameId, int playerId);
    GameDtoBuilder removePlayerFromGame(int gameId, int playerId);
}
