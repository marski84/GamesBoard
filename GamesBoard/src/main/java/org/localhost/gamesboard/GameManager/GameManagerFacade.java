package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameManagerFacade {
    GameWithStartDateDto startGame(int gameId);
    GameWithFinishDateDto endGame(int gameId);
    GameResultDto saveGameScore(int gameId, List<PlayerScore> gameScore);
    boolean isGameActive(int gameId);
    List<Player> getPlayersRegisteredInGame(int gameId);
    boolean isPlayerRegisteredInGame(int gameId, int playerId);

}
