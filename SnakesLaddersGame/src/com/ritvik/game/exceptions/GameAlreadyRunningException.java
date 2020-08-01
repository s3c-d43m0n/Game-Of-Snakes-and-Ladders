package com.ritvik.game.exceptions;

public class GameAlreadyRunningException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameAlreadyRunningException() {
		super("Game is in Running State");
	}

}
