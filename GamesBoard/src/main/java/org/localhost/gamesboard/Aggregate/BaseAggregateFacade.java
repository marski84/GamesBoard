package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.localhost.gamesboard.model.Game;
import org.springframework.stereotype.Service;


@Service
public class BaseAggregateFacade implements AggregateFacade {

    private final BaseAggregateService baseAggregateService;

    public BaseAggregateFacade(BaseAggregateService baseAggregateService) {
        this.baseAggregateService = baseAggregateService;
    }

    @Override
    public GameWithPlayersDto addPlayerToGame(int gameId, int playerId) {
        Game game = baseAggregateService.registerPlayerOnTheGame(gameId, playerId);

        GameWithPlayersDto gameWithPlayersDto = new GameWithPlayersDto();
        gameWithPlayersDto.setGameName(game.getGameName());
        gameWithPlayersDto.setPlayers(game.getPlayers());
        gameWithPlayersDto.setCreationDate(game.getCreatedAt());
        return gameWithPlayersDto;
    }

    @Override
    public GameWithPlayersDto removePlayerFromGame(int gameId, int playerId) {
        Game game = baseAggregateService.unregisterPlayerFromTheGame(gameId, playerId);

        GameWithPlayersDto gameWithPlayersDto = new GameWithPlayersDto();
        gameWithPlayersDto.setGameName(game.getGameName());
        gameWithPlayersDto.setPlayers(game.getPlayers());
        gameWithPlayersDto.setCreationDate(game.getCreatedAt());
        return gameWithPlayersDto;
    }
}
