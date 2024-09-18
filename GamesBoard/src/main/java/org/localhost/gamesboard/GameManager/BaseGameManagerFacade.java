package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class BaseGameManagerFacade implements GameManagerFacade {
    private final GameManagerService gameManagerService;

    public BaseGameManagerFacade(GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
    }

    @Override
    public GameWithStartDateDto startGame(int gameId) {
        Game game = gameManagerService.startGame(gameId);

        GameWithStartDateDto gameWithStartDateDto = new GameWithStartDateDto();
        gameWithStartDateDto.setStartDate(game.getGameFinishDate());
        gameWithStartDateDto.setGameName(game.getGameName());
        gameWithStartDateDto.setCreationDate(game.getCreatedAt());
        return gameWithStartDateDto;
    }

    @Override
    public GameWithFinishDateDto endGame(int gameId) {
        Game game = gameManagerService.endGame(gameId);

        GameWithFinishDateDto gameWithFinishDateDto = new GameWithFinishDateDto();
        gameWithFinishDateDto.setFinishDate(game.getGameFinishDate());
        gameWithFinishDateDto.setGameName(game.getGameName());
        gameWithFinishDateDto.setCreationDate(game.getCreatedAt());

        return gameWithFinishDateDto;
    }

    @Override
    public GameResultDto saveGameScore(int gameId, List<PlayerScore> gameScore) {
        Game game = gameManagerService.saveGameScore(gameId, gameScore);
        GameResultDto gameResultDto = new GameResultDto();
        gameResultDto.setGameName(game.getGameName());
        gameResultDto.setScore(game.getGameScore());
        gameResultDto.setCreationDate(game.getCreatedAt());
        return gameResultDto;
    }

    public boolean isGameActive(int gameId) {
        return gameManagerService.isGameActive(gameId);
    }

    @Override
    public List<Player> getPlayersRegisteredInGame(int gameId) {
        return gameManagerService.getPlayersInGame(gameId);
    }

    @Override
    public boolean isPlayerRegisteredInGame(int gameId, int playerId) {
        return gameManagerService.isPlayerInGame(gameId, playerId);
    }
}
