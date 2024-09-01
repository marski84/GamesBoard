package org.localhost.gamesboard.service;

import org.localhost.gamesboard.exceptions.PlayerNotFoundException;
import org.localhost.gamesboard.model.Player;

import java.util.Optional;

public interface PlayerService {
    Player addPlayer(Player player);
    Player removePlayer(int playerId) throws PlayerNotFoundException;
    Player getPlayerById(int playerId);
}
