package org.localhost.gamesboard.GameManager;

import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.GameManager.model.PlayerScore;

import java.util.List;

public interface GameManagerService {
    // Operacje zarządzania stanem gry
    Game startGame(int gameId);
    Game endGame(int gameId);
    Game saveGameScore(int gameId, List<PlayerScore> gameScore);
    // Metody pomocnicze związane z logiką biznesową gry
    boolean isGameActive(int gameId);
    List<Player> getPlayersInGame(int gameId);
    boolean isPlayerInGame(int gameId, int playerId);
}
