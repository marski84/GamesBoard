package org.localhost.gamesboard.exceptions;

import org.localhost.gamesboard.exceptions.messages.ExceptionMessages;

public class GameNotStartedException extends RuntimeException {
public GameNotStartedException() {
    super(ExceptionMessages.GAME_NOT_STARTED);
}
}
