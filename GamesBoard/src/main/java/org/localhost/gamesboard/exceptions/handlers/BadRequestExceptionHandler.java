package org.localhost.gamesboard.exceptions.handlers;

import org.localhost.gamesboard.exceptions.GameStateException;
import org.localhost.gamesboard.exceptions.PlayerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({
            GameStateException.class,
            PlayerException.class,
            IllegalArgumentException.class,
            PlayerException.class,
    }
    )
    public ResponseEntity<?> gameExceptionHandler(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }



}
