package org.localhost.gamesboard.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            GameAlreadyFinishedException.class,
            GameAlreadyStartedException.class,
            GameNotFoundException.class,
            GameNotFoundException.class,
            PlayerNotFoundException.class,
            IllegalArgumentException.class
    }
    )
    public ResponseEntity<?> gameExceptionHandler(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }


}
