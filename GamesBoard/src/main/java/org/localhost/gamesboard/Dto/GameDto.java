package org.localhost.gamesboard.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class GameDto {
    private String gameName;
    private LocalDateTime creationDate;

}
