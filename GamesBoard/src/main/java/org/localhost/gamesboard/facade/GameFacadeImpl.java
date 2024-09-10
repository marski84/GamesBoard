package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.exceptions.GameAlreadyExistsException;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import org.localhost.gamesboard.service.GameService;
import org.localhost.gamesboard.service.PlayerService;
import org.localhost.gamesboard.service.PlayerServiceImpl;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class GameFacadeImpl implements GameFacade {

    private final GameService gameService;
    private final PlayerService playerServiceImpl;


    public GameFacadeImpl(GameService gameServiceImpl, PlayerServiceImpl playerServiceImpl) {
        this.gameService = gameServiceImpl;
        this.playerServiceImpl = playerServiceImpl;
    }


    @Override

    public GameDto createGame(String gameName) {
        if (gameName == null || gameName.isEmpty()) {
            throw new IllegalArgumentException("Game name cannot be null or empty");
        }
        Game game = new Game();
        game.setGameName(gameName);
        game.setCreatedAt(LocalDateTime.now());

        if (gameService.gameExists(gameName)) {
            throw new GameAlreadyExistsException();
        }

        Game savedGame = gameService.registerNewGame(game);
        GameDto gameDto = new GameDto();
        gameDto.setGameName(savedGame.getGameName());
        return gameDto;
    }

    @Override
    public GameWithStartDateDto startGame(int gameId) {
        Game game = gameService.startGame(gameId);

        GameWithStartDateDto gameWithStartDateDto = new GameWithStartDateDto();
        gameWithStartDateDto.setGameName(game.getGameName());
        gameWithStartDateDto.setStartDate(game.getCreatedAt());
        gameWithStartDateDto.setCreationDate(game.getCreatedAt());
        return gameWithStartDateDto;
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
    public GameWithFinishDateDto endGame(int gameId) {
        Game game = gameService.endGame(gameId);

        GameWithFinishDateDto gameWithFinishDateDto = new GameWithFinishDateDto();
        gameWithFinishDateDto.setFinishDate(game.getGameFinishDate());
        gameWithFinishDateDto.setGameName(game.getGameName());
        gameWithFinishDateDto.setCreationDate(game.getCreatedAt());

        return gameWithFinishDateDto;
    }

    @Override
    public Player registerPlayer(String playerName) {
        if (playerName == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        return gameService.registerPlayer(playerName);

    }

    @Override
    public Player removePlayer(int playerId) {
        return gameService.removePlayer(playerId);
    }

    @Override
    public GameResultDto saveGameScore(int gameId, List<PlayerScore> gameScore) {
        Game game = gameService.saveGameScore(gameId, gameScore);
        GameResultDto gameResultDto = new GameResultDto();
        gameResultDto.setGameName(game.getGameName());
        gameResultDto.setScore(game.getPlayersScores());
        gameResultDto.setCreationDate(game.getCreatedAt());
        return gameResultDto;
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
