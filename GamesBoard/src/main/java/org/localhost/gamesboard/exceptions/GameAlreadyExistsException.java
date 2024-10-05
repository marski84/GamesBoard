package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class GameAlreadyExistsException extends RuntimeException {
    public GameAlreadyExistsException() {
        super(ExceptionMessages.GAME_EXISTS);
    }
}
