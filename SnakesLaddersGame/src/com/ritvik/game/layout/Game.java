package com.ritvik.game.layout;

import com.ritvik.game.exceptions.*;

// All the rules of game 
public interface Game {
	
	// to add player in the game using names (Player name should be unique)
	public void addPlayer(String name) throws GameAlreadyRunningException;
	
	// to remove player in the game using names
	public void removePlayer(String name) throws PlayerNotFoundException, GameAlreadyRunningException;
	
	// to add Snakes on board ( start > end ) 
	public void addSnake(int start, int end) throws GameAlreadyRunningException, SnakeOrLadderException;
	
	// to remove Snakes on board on start position 
	public void removeSnake(int start) throws GameAlreadyRunningException, SnakeOrLadderException;
	
	// to add Ladder on the board ( start < end )
	public void addLadder(int start, int end) throws GameAlreadyRunningException, SnakeOrLadderException;
	
	// to remove Ladder on the board on start position
	public void removeLadder(int start) throws GameAlreadyRunningException, SnakeOrLadderException;
	
	// to start GAME - it will start the game, 
	// If started, 
	//   - then no players can be added, need to stop game if want to add player
	//   - and no snake/ladder can be added, need to stop game if want to add snake/ladder
	public void start() throws GameAlreadyRunningException;
	
	// to reset GAME - it will move all the player to start point
	public void reset();
	
	// to stop GAME - it will stop the game. if any player wins the game, then also need to stop
	public void stop() throws GameNotRuningException;;

	// get name of Current Player
	public String getNameOfCurentPlayer() throws GameNotRuningException;

	// to throw normal dice for current player -> Normal dice return random in 1,2,3,4,5,6
	public void throwNormalDice() throws GameNotRuningException;
	
	// to throw crooked dice for current player -> Crooked dice return random in 2,4,6
	public void throwCrookedDice() throws GameNotRuningException;
	
	// to get Position of player
	public int getPositionOfPlayer(String name) throws PlayerNotFoundException;
	
	// to check if game is over
	public boolean isGameOver();
}
