package org.localhost.gamesboard.Dto;
import lombok.Getter;
import lombok.Setter;
import org.localhost.gamesboard.model.Player;
import org.localhost.gamesboard.model.PlayerScore;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class GameDataDto extends GameDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<PlayerScore> playerScores;
    private List<Player> players;

}
