package org.localhost.gamesboard.facade;

import org.localhost.gamesboard.Dto.PlayerDataDto;
import org.localhost.gamesboard.model.Player;

import java.util.Optional;

public interface PlayerFacade {
     PlayerDataDto getPlayer(int playerId);
     PlayerDataDto getPlayer(String playerName);
     PlayerDataDto addPlayer(String playerName);
     PlayerDataDto removePlayer(int playerId);

}
