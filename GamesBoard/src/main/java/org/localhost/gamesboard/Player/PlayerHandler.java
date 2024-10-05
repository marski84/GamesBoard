package org.localhost.gamesboard.Player;

import org.localhost.gamesboard.Player.dto.PlayerDataDto;

public interface PlayerHandler {
     PlayerDataDto getPlayer(int playerId);
     PlayerDataDto getPlayer(String playerName);
     PlayerDataDto addPlayer(String playerName);
     PlayerDataDto removePlayer(int playerId);

}
