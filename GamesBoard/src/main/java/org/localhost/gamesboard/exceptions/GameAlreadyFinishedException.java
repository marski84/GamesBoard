package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class GameAlreadyFinishedException extends RuntimeException {
    public GameAlreadyFinishedException() {
        super(ExceptionMessages.GAME_FINISHED);
    }
}
