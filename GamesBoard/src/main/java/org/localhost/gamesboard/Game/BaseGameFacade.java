package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.GameManager.GameManagerService;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.Player.BasePlayerService;
import org.springframework.stereotype.Service;


import java.util.Objects;

@Service
public class BaseGameFacade implements GameFacade {

    private final GameService gameService;

    public BaseGameFacade(GameService gameServiceImpl, BasePlayerService basePlayerService, GameManagerService gameManagerService) {
        this.gameService = gameServiceImpl;
    }






    @Override
    public GameDataDto getGameByName(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        Game game = gameService.getGameByName(gameName);
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
        dto.setPlayerScores(game.getGame_score());
        return dto;
    }


}
