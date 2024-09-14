package org.localhost.gamesboard.exceptions;

public class GameNotActiveException extends RuntimeException {
    public GameNotActiveException() {
        super("Game not active");
    }
}
