package com.ritvik.game.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(String name) {
		super("Player '"+name+"' is not available");
	}
}
