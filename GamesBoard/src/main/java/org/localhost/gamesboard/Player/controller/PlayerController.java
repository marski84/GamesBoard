package org.localhost.gamesboard.Player.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.localhost.gamesboard.Player.dto.PlayerDataDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PlayerController {

    ResponseEntity<PlayerDataDto> addPlayer(@PathVariable @NotNull String playerName);

    ResponseEntity<PlayerDataDto> removePlayer(@PathVariable @NotNull @Positive int playerId);

    ResponseEntity<PlayerDataDto> getPlayer(@PathVariable @NotNull String playerName);

    ResponseEntity<PlayerDataDto> getPlayer(@PathVariable @NotNull @Positive int playerId);
}
