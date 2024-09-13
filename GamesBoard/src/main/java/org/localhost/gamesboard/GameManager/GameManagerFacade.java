package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Dto.GameDto;
import org.localhost.gamesboard.Dto.GameResultDto;
import org.localhost.gamesboard.Dto.GameWithFinishDateDto;
import org.localhost.gamesboard.Dto.GameWithStartDateDto;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameManagerFacade {
    GameWithStartDateDto startGame(int gameId);
    public GameWithFinishDateDto endGame(int gameId);
    GameResultDto saveGameScore(int gameId, List<PlayerScore> gameScore);
    GameDto createGame(String gameName);

}
