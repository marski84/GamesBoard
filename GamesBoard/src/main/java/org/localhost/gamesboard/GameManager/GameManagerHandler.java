package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Game.dto.GameDtoBuilder;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.GameManager.model.PlayerScore;

import java.util.List;

public interface GameManagerHandler {
    GameDtoBuilder startGame(int gameId);

    GameDtoBuilder endGame(int gameId);

    GameDtoBuilder saveGameScore(int gameId, List<PlayerScore> gameScore);

    boolean isGameActive(int gameId);

    List<Player> getPlayersRegisteredInGame(int gameId);

    boolean isPlayerRegisteredInGame(int gameId, int playerId);

}
