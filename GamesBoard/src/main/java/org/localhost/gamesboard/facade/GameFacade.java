package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.Dto.GameDataDto;
import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameFacade {
    GameDto createGame(String gameName);
    GameWithStartDateDto startGame(int gameId);
    Game addPlayerToGame(int gameId, int playerId);
    Game removePlayerFromGame(int gameId, int playerId);
    GameWithFinishDateDto endGame(int gameId);
    Player registerPlayer(String playerName);
    Player removePlayer(int playerId);
    Game saveGameScore(int gameId, List<PlayerScore> gameScore);
    GameDataDto getGameByName(String gameName);
    GameDataDto getGameById(int gameId);

}
