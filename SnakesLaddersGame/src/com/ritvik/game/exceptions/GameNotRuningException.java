package com.ritvik.game.exceptions;

public class GameNotRuningException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GameNotRuningException(){
		super("Game is not in running state");
	}
}
