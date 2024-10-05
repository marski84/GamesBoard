package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Game.model.Game;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class BaseGameHandler implements GameHandler {

    private final GameService gameService;

    public BaseGameHandler(GameService gameServiceImpl) {
        this.gameService = gameServiceImpl;
    }


    @Override
    public GameDtoBuilder getGameByName(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        Game game = gameService.getGameByName(gameName);
        return createGameDataDto(game);
    }

    @Override
    public GameDtoBuilder getGameById(int gameId) {
        Game game = gameService.getGameById(gameId);
        return createGameDataDto(game);
    }

    @Override
    public GameDtoBuilder updateGame(Game game) {
        Game foundGame = gameService.updateGame(game);
        return createGameDataDto(foundGame);
    }

    @Override
    public GameDtoBuilder createGame(String gameName) {
        Game game = new Game();
        game.setCreatedAt(Instant.now());
        game.setGameName(gameName);

        Game savedGame = gameService.createGame(game);
        return createGameDataDto(savedGame);
    }

    @Override
    public void deleteGame(int gameId) {
        gameService.deleteGame(gameId);
    }

    @Override
    public List<GameDtoBuilder> getAllGames() {
        List<Game> gamesList = gameService.getAllGames();
        return gamesList.stream().map(this::createGameDataDto).toList();
    }


    private GameDtoBuilder createGameDataDto(Game game) {
        return GameDtoBuilder.builder().gameName(game.getGameName()).creationDate(game.getCreatedAt()).startDate(game.getGameStartDate()).players(game.getPlayers()).playerScores(game.getGameScore()).build();
    }


}
