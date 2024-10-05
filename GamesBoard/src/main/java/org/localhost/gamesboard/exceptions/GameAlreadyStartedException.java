package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class GameAlreadyStartedException extends RuntimeException {
    public GameAlreadyStartedException() {
        super(ExceptionMessages.GAME_STARTED);
    }

}
