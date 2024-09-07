package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
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
    public Game addPlayerToGame(int gameId, int playerId) {
        return gameService.registerPlayerOnTheGame(gameId, playerId);
    }

    @Override
    public Game removePlayerFromGame(int gameId, int playerId) {
        return gameService.unregisterPlayerFromTheGame(gameId, playerId);
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
    public Game saveGameScore(int gameId, List<PlayerScore> gameScore) {
        return gameService.saveGameScore(gameId, gameScore);
    }

    @Override
    public GameDataDto getGameByName(String gameName) {
        Objects.requireNonNull(gameName, "Game name cannot be null");
        Game game = gameService.findGameByName(gameName);

        GameDataDto dto = new GameDataDto();
        dto.setGameName(game.getGameName());
        dto.setStartDate(game.getGameStartDate());
        dto.setCreationDate(game.getCreatedAt());
        dto.setPlayers(game.getPlayers());
        dto.setPlayerScores(game.getPlayersScores());
        return dto;
    }

    @Override
    public GameDataDto getGameById(int gameId) {
        Game game = gameService.getGameById(gameId);

        GameDataDto dto = new GameDataDto();
        dto.setGameName(game.getGameName());
        dto.setStartDate(game.getGameStartDate());
        dto.setCreationDate(game.getCreatedAt());
        dto.setPlayers(game.getPlayers());
        dto.setPlayerScores(game.getPlayersScores());
        return dto;
    }


}
