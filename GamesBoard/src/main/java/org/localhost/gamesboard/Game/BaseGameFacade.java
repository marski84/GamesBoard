package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.GameManager.GameManagerService;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;
import org.localhost.gamesboard.Player.BasePlayerService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class BaseGameFacade implements GameFacade {

    private final GameService gameService;

    public BaseGameFacade(GameService gameServiceImpl, BasePlayerService basePlayerService, GameManagerService gameManagerService) {
        this.gameService = gameServiceImpl;
    }


    @Override
    public GameWithPlayersDto addPlayerToGame(int gameId, int playerId) {
        Game game = gameService.registerPlayerOnTheGame(gameId, playerId);

        GameWithPlayersDto gameWithPlayersDto = new GameWithPlayersDto();
        gameWithPlayersDto.setGameName(game.getGameName());
        gameWithPlayersDto.setPlayers(game.getPlayers());
        gameWithPlayersDto.setCreationDate(game.getCreatedAt());
        return gameWithPlayersDto;
    }

    @Override
    public GameWithPlayersDto removePlayerFromGame(int gameId, int playerId) {
        Game game = gameService.unregisterPlayerFromTheGame(gameId, playerId);

        GameWithPlayersDto gameWithPlayersDto = new GameWithPlayersDto();
        gameWithPlayersDto.setGameName(game.getGameName());
        gameWithPlayersDto.setPlayers(game.getPlayers());
        gameWithPlayersDto.setCreationDate(game.getCreatedAt());
        return gameWithPlayersDto;
    }





    @Override
    public GameDataDto getGameByName(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        Game game = gameService.findGameByName(gameName);
        return createGameDataDto(game);
    }

    @Override
    public GameDataDto getGameById(int gameId) {
        Game game = gameService.getGameById(gameId);
        return createGameDataDto(game);
    }

    private GameDataDto createGameDataDto(Game game) {
        GameDataDto dto = new GameDataDto();
        dto.setGameName(game.getGameName());
        dto.setStartDate(game.getGameStartDate());
        dto.setCreationDate(game.getCreatedAt());
        dto.setPlayers(game.getPlayers());
        dto.setPlayerScores(game.getPlayersScores());
        return dto;
    }


}
