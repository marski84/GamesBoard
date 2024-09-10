package org.localhost.gamesboard.Dto;

import lombok.Getter;
import lombok.Setter;
import org.localhost.gamesboard.model.PlayerScore;

import java.util.List;

@Getter
@Setter
public class GameResultDto extends GameDto {
    private List<PlayerScore> score;
}
