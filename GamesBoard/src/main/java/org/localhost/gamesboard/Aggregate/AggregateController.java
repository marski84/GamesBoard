package org.localhost.gamesboard.Aggregate;

import org.localhost.gamesboard.Dto.GameWithPlayersDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage-game-players")
public class AggregateController {
    private final AggregateFacade aggregateFacade;

    public AggregateController(AggregateFacade aggregateFacade) {
        this.aggregateFacade = aggregateFacade;
    }


    @PostMapping("/register/{gameId}/{playerId}")
    public ResponseEntity<GameWithPlayersDto> registerPlayer(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        GameWithPlayersDto gameWithPlayersDto = aggregateFacade.addPlayerToGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameWithPlayersDto);
    }

    @PostMapping("/unregister/{gameId}/{playerId}")
    public ResponseEntity<Void> unregisterPlayer(
            @PathVariable int gameId,
            @PathVariable int playerId
    ) {
        aggregateFacade.removePlayerFromGame(gameId, playerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
