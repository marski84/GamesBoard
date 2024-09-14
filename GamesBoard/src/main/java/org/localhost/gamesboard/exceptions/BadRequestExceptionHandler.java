package org.localhost.gamesboard.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({
            GameAlreadyFinishedException.class,
            GameAlreadyStartedException.class,
            IllegalArgumentException.class
    }
    )
    public ResponseEntity<?> gameExceptionHandler(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }



}
