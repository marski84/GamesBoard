package org.localhost.gamesboard.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class GameWithStartDateDto extends GameDto {
    private LocalDateTime startDate;

}
