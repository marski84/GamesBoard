package org.localhost.gamesboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PlayerScore {
    private String nickName;
    private Integer score;
}
