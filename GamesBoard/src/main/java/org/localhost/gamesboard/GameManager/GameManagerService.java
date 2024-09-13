package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.model.Game;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

public interface GameManagerService {
    Game registerNewGame(Game game);
    Game startGame(int gameId);
    Game endGame(int gameId);
    Game saveGameScore(int gameId, List<PlayerScore> gameScore);
    Game getGameById(int gameId);

}
