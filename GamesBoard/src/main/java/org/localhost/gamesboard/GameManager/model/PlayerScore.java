package org.localhost.gamesboard.GameManager.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerScore {
    private String playerName;
    private Integer score;
}
