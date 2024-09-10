package org.localhost.gamesboard.Dto;

import lombok.Getter;
import lombok.Setter;
import org.localhost.gamesboard.model.Player;

import java.util.List;

@Getter
@Setter
public class GameWithPlayersDto extends GameDto {
    private List<Player> players;
}
