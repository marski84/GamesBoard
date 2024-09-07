package org.localhost.gamesboard.exceptions;

public class PlayerWithNicknameAlreadyExistException extends RuntimeException {
    public PlayerWithNicknameAlreadyExistException() {
        super("Player with nickname already exists");
    }
}
