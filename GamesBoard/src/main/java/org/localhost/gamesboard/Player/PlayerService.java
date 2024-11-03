package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.exceptions.PlayerException;

public interface PlayerService {
    Player registerPlayer(String playerName);

    Player removePlayer(int playerId) throws PlayerException;
    Player getPlayerData(int playerId);
    Player getPlayerData(String playerName);
}
