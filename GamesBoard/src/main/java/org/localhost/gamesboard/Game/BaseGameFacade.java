package org.localhost.gamesboard.Game;

import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.model.Game;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class BaseGameFacade implements GameFacade {

    private final GameService gameService;

    public BaseGameFacade(GameService gameServiceImpl) {
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

    @Override
    public GameDataDto updateGame(Game game) {
        Game foundGame = gameService.updateGame(game);
        return createGameDataDto(foundGame);
    }

    @Override
    public GameDataDto createGame(String gameName) {
        Game game = new Game();
        game.setCreatedAt(LocalDateTime.now());
        game.setGameName(gameName);

        Game savedGame =  gameService.createGame(game);
        return createGameDataDto(savedGame);
    }

    @Override
    public void deleteGame(int gameId) {
        gameService.deleteGame(gameId);
    }

    @Override
    public List<GameDataDto> getAllGames() {
        List<Game> gamesList = gameService.getAllGames();
        return gamesList.stream()
                .map(this::createGameDataDto)
                .toList();
    }



    private GameDataDto createGameDataDto(Game game) {
        GameDataDto dto = new GameDataDto();
        dto.setGameName(game.getGameName());
        dto.setStartDate(game.getGameStartDate());
        dto.setCreationDate(game.getCreatedAt());
        dto.setPlayers(game.getPlayers());
        dto.setPlayerScores(game.getGameScore());
        return dto;
    }


}
