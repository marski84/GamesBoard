package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.model.Player;

public interface PlayerService {
    Player registerPlayer(String playerName);
    Player removePlayer(int playerId) throws PlayerNotFoundException;
    Player getPlayerData(int playerId);
    Player getPlayerData(String playerName);
}
