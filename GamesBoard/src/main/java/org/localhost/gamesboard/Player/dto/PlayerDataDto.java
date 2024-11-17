package org.localhost.gamesboard.Player.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.localhost.gamesboard.Player.model.Player;

@Setter
@Getter
@Builder
public class PlayerDataDto {
    private int playerId;
    private String playerName;

    public static PlayerDataDto fromPlayer(Player player) {
        return PlayerDataDto.builder()
                .playerId(player.getId())
                .playerName(player.getPlayerNickname())
                .build();
    }

}
