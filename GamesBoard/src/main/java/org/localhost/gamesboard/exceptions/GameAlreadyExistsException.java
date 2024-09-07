package org.localhost.gamesboard.exceptions;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException() {
        super("Game with selected name already exists");
    }
}
