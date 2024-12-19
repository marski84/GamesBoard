package org.localhost.gamesboard.Game.controller;

import org.localhost.gamesboard.Game.dto.GameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GameController {
    ResponseEntity<GameDto> getGame(@PathVariable int gameId);

    ResponseEntity<GameDto> getGame(@PathVariable String gameName);

    ResponseEntity<List<GameDto>> getAllGames();

    ResponseEntity<Void> deleteGame(@PathVariable int gameId);

    ResponseEntity<GameDto> createGame(@PathVariable String gameName);
}
