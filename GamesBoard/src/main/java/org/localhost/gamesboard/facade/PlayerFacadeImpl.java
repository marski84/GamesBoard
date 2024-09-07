package org.localhost.gamesboard.facade;
import org.localhost.gamesboard.Dto.PlayerDataDto;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.service.PlayerService;
import org.springframework.stereotype.Service;
@Service
public class PlayerFacadeImpl implements PlayerFacade {
    private final PlayerService playerService;

    public PlayerFacadeImpl(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public PlayerDataDto getPlayer(int playerId) {
        Player player = playerService.getPlayerData(playerId);
        PlayerDataDto playerDataDto = new PlayerDataDto();
        playerDataDto.setPlayerId(player.getId());
        playerDataDto.setPlayerName(player.getPlayerNickname());
        return playerDataDto;
    }

    @Override
    public PlayerDataDto getPlayer(String playerName) {
        Player player = playerService.getPlayerData(playerName);
        PlayerDataDto playerDataDto = new PlayerDataDto();
        playerDataDto.setPlayerId(player.getId());
        playerDataDto.setPlayerName(player.getPlayerNickname());
        return playerDataDto;
    }

    @Override
    public PlayerDataDto addPlayer(String playerName) {
        Player player = playerService.registerPlayer(playerName);
        PlayerDataDto playerDataDto = new PlayerDataDto();
        playerDataDto.setPlayerId(player.getId());
        playerDataDto.setPlayerName(player.getPlayerNickname());
        return playerDataDto;
    }

    @Override
    public PlayerDataDto removePlayer(int playerId) {
        Player player = playerService.removePlayer(playerId);
        PlayerDataDto playerDataDto = new PlayerDataDto();
        playerDataDto.setPlayerId(player.getId());
        playerDataDto.setPlayerName(player.getPlayerNickname());
        return playerDataDto;
    }
}
