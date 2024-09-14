package org.localhost.gamesboard.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler({
            GameNotFoundException.class,
            PlayerNotFoundException.class,
    })     public ResponseEntity<?> gameExceptionHandler(Exception e) {
        return ResponseEntity
                .ok()
                .body(e.getMessage());
    }
}
