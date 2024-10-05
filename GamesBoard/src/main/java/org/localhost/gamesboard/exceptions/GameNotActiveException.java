package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class GameNotActiveException extends RuntimeException {
    public GameNotActiveException() {
        super(ExceptionMessages.GAME_NOT_ACTIVE);
    }
}
