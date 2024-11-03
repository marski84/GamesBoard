package org.localhost.gamesboard.exceptions.messages;

public enum PlayerErrorCode {
    PLAYER_ALREADY_IN_GAME(2000, "Player is already in the game"),
    PLAYER_NOT_FOUND(2001, "Player not found"),
    PLAYER_NOT_REGISTERED(2002, "Player is not registered in the game"),
    PLAYER_NICKNAME_EXISTS(2003, "Player with this nickname already exists");

    private final int code;
    private final String defaultMessage;

    PlayerErrorCode(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
