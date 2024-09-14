package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.Game.GameService;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BaseGameManagerFacade implements GameManagerFacade {
    private final GameManagerService gameManagerService;
    private final GameService gameService;

    public BaseGameManagerFacade(GameManagerService gameManagerService, GameService gameService) {
        this.gameManagerService = gameManagerService;
        this.gameService = gameService;
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
        gameResultDto.setScore(game.getGame_score());
        gameResultDto.setCreationDate(game.getCreatedAt());
        return gameResultDto;
    }

    @Override
    public GameDto createGame(String gameName) {
        Game game = new Game();
        game.setGameName(gameName);
        game.setCreatedAt(LocalDateTime.now());

        Game savedGame = gameService.createGame(game);

        GameDto gameDto = new GameDto();
        gameDto.setGameName(savedGame.getGameName());
        gameDto.setCreationDate(game.getCreatedAt());
        return gameDto;
    }
}
