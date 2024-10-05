package org.localhost.gamesboard.exceptions.messages;

public final class ExceptionMessages {
    public static final String GAME_EXISTS = "Game with selected name already exists";
    public static final String GAME_FINISHED = "Game already finished!";
    public static final String GAME_STARTED = "Game already started";
    public static final String GAME_NOT_ACTIVE = "Game not active";
    public static final String GAME_NOT_STARTED = "Game not started";
    public static final String PLAYER_ALREADY_REGISTERED = "Player is already in a game!";
    public static final String PLAYER_NOT_FOUND = "Player not found!";
    public static final String PLAYER_NOT_REGISTERED = "Player is not registered for this game.";
    public static final String PLAYER_WITH_NICKNAME_EXISTS = "Player with nickname already exists";

    private ExceptionMessages() {}
}