package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.Dto.*;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameFacade {
    GameDto createGame(String gameName);
    GameWithStartDateDto startGame(int gameId);
    GameWithPlayersDto addPlayerToGame(int gameId, int playerId);
    GameWithPlayersDto removePlayerFromGame(int gameId, int playerId);
    GameWithFinishDateDto endGame(int gameId);
    Player registerPlayer(String playerName);
    Player removePlayer(int playerId);
    GameResultDto saveGameScore(int gameId, List<PlayerScore> gameScore);
    GameDataDto getGameByName(String gameName);
    GameDataDto getGameById(int gameId);

}
