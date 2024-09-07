package org.localhost.gamesboard.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class GameWithFinishDateDto extends GameDto {
    private LocalDateTime finishDate;
}
