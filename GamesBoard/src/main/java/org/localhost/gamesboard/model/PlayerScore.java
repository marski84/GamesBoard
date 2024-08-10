package org.localhost.gamesboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerScore {
    private String nickName;
    private Integer score;

    // getters, setters, constructors
}
