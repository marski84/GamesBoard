package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.Dto.PlayerDataDto;

public interface PlayerFacade {
     PlayerDataDto getPlayer(int playerId);
     PlayerDataDto getPlayer(String playerName);
     PlayerDataDto addPlayer(String playerName);
     PlayerDataDto removePlayer(int playerId);

}
