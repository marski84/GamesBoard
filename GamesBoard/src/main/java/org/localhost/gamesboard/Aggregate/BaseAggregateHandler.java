package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Game.model.Game;
import org.springframework.stereotype.Service;


@Service
public class BaseAggregateHandler implements AggregateHandler {

    private final AggregateService baseAggregateService;

    public BaseAggregateHandler(AggregateService baseAggregateService) {
        this.baseAggregateService = baseAggregateService;
    }

    @Override
    public GameDtoBuilder addPlayerToGame(int gameId, int playerId) {
        Game game = baseAggregateService.registerPlayerOnTheGame(gameId, playerId);
        GameDtoBuilder gameDto = GameDtoBuilder.builder().gameName(game.getGameName()).startDate(game.getGameStartDate()).players(game.getPlayers()).build();
        return gameDto;
    }

    @Override
    public GameDtoBuilder removePlayerFromGame(int gameId, int playerId) {
        Game game = baseAggregateService.unregisterPlayerFromTheGame(gameId, playerId);

        GameDtoBuilder gameDto = GameDtoBuilder.builder().gameName(game.getGameName()).creationDate(game.getCreatedAt()).players(game.getPlayers()).build();
        return gameDto;
    }
}
