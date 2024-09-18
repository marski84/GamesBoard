package org.localhost.gamesboard.exceptions.handlers;

import org.localhost.gamesboard.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({
            GameAlreadyFinishedException.class,
            GameAlreadyStartedException.class,
            GameNotStartedException.class,
            GameAlreadyFinishedException.class,
            IllegalArgumentException.class,
            PlayerNotRegisteredInGameException.class,
            PlayerAlreadyInGameException.class,
            PlayerWithNicknameAlreadyExistException.class
    }
    )
    public ResponseEntity<?> gameExceptionHandler(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }



}
