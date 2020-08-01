package com.ritvik.game.impl;

import java.util.*;

import javax.management.RuntimeErrorException;

import com.ritvik.game.exceptions.*;
import com.ritvik.game.layout.*;

public class GameImpl implements Game {

	private Boolean isGameRunning;
	private Boolean isGameOver;
	
	private int gameBoardSize;
	private int currentPlayer;
	
	private ArrayList<String> playerList;
	private HashMap<String,Integer> playerPosition;
	private HashMap<Integer,Integer> snakeLadder;
	
	private Dice crookedDice, normalDice;
	
	public GameImpl(int boardSize) throws RuntimeException{
		if(boardSize<=0) {
			throw new RuntimeException("Game can't have board size less than 1");
		}
		
		gameBoardSize = boardSize;
		isGameRunning=false;
		isGameOver=false;
		currentPlayer=0;
		
		playerList = new ArrayList<String>();
		playerPosition = new HashMap<String, Integer>();
		snakeLadder = new HashMap<Integer, Integer>();
		
		crookedDice = new CrookedDice();
		normalDice  = new NormalDice();
		
		System.out.println("Game of Snakes & Ladders is READY with board size "+boardSize);
	}
	
	@Override
	public void addPlayer(String name) throws GameAlreadyRunningException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		playerList.add(name);
		playerPosition.put(name, 0);
		System.out.println(name+"\t-> added to player list");
	}

	@Override
	public void removePlayer(String name) throws PlayerNotFoundException, GameAlreadyRunningException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(playerPosition.containsKey(name)) {
			playerList.remove(name);
			playerPosition.remove(name);
			System.out.println(name+"\t-> removed from player list");
		}
		else {
			throw new PlayerNotFoundException(name);
		}
	}

	@Override
	public void addSnake(int start, int end) throws GameAlreadyRunningException, SnakeOrLadderException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(start>=gameBoardSize) {
			throw new SnakeOrLadderException("For Snake, start position("+start+") should be less than board size("+gameBoardSize+")");
		}
		else if(end<1) {
			throw new SnakeOrLadderException("For Snake, end position("+end+") should be greater than 0");
		}
		else if(start>end) {
			if(snakeLadder.containsKey(start)) {
				int value = snakeLadder.get(start);
				throw new SnakeOrLadderException("At position("+start+") "+((start-value)>0 ? "snake":"ladder")+" already exist.");
			}
			else {
				snakeLadder.put(start, end);
				System.out.println("Snake at "+start+"->"+end+" position added to board.");
			}
		}
		else {
			throw new SnakeOrLadderException("For Snake, start position("+start+") should be greater than end position("+end+")");
		}
		
	}

	@Override
	public void removeSnake(int start) throws GameAlreadyRunningException, SnakeOrLadderException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(snakeLadder.containsKey(start) && start-snakeLadder.get(start)>0){
			snakeLadder.remove(start);
			System.out.println("Snake at "+start+" position removed from board.");
		}
		else {
			throw new SnakeOrLadderException("At position("+start+") snake not found");
		}
	}

	@Override
	public void addLadder(int start, int end) throws GameAlreadyRunningException, SnakeOrLadderException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(end>=gameBoardSize) {
			throw new SnakeOrLadderException("For Ladder, end position("+end+") should be less than board size("+gameBoardSize+")");
		}
		else if(start<1) {
			throw new SnakeOrLadderException("For Ladder, start position("+start+") should be greater than 0");
		}
		else if(start>=end) {
			throw new SnakeOrLadderException("For Ladder, end position("+end+") should be greater than start position("+start+")");
		}
		else {
			if(snakeLadder.containsKey(start)) {
				int value = snakeLadder.get(start);
				throw new SnakeOrLadderException("At position("+start+") "+((start-value)>0 ? "snake":"ladder")+" already exist.");
			}
			else {
				snakeLadder.put(start, end);
				System.out.println("Ladder at "+start+"->"+end+" position added to board.");
			}
		}
	}

	@Override
	public void removeLadder(int start) throws GameAlreadyRunningException, SnakeOrLadderException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(snakeLadder.containsKey(start) && start-snakeLadder.get(start)<0){
			snakeLadder.remove(start);
			System.out.println("Ladder at "+start+" position removed from board.");
		}
		else {
			throw new SnakeOrLadderException("At position("+start+") ladder not found");
		}
	}

	@Override
	public void start() throws GameAlreadyRunningException {
		if(isGameRunning) throw new GameAlreadyRunningException();
		if(playerList.size()>0) {
			System.out.println("Starting the GAME");
			isGameRunning=true;
			isGameOver=false;
		}
	}

	@Override
	public void reset() {
		for(String player : playerPosition.keySet()) {
			playerPosition.put(player, 0);
			System.out.println(player+"\t-> Position set to 0 (RESET)");
		}		
	}

	@Override
	public void stop() throws GameNotRuningException {
		if(!isGameRunning) throw new GameNotRuningException();
		System.out.println("Stopping the GAME");
		reset();
		isGameRunning=false;
		isGameOver=true;
	}

	@Override
	public String getNameOfCurentPlayer() throws GameNotRuningException {
		if(!isGameRunning) throw new GameNotRuningException();
		return playerList.get(currentPlayer);
	}

	@Override
	public void throwNormalDice() throws GameNotRuningException {
		if(!isGameRunning) throw new GameNotRuningException();
		System.out.println(playerList.get(currentPlayer)+"\t-> Throwing Normal Dice");
		moveCurrentPlayer(normalDice.getNextMove());
	}

	@Override
	public void throwCrookedDice() throws GameNotRuningException {
		if(!isGameRunning) throw new GameNotRuningException();
		System.out.println(playerList.get(currentPlayer)+"\t-> Throwing Crooked Dice");
		moveCurrentPlayer(crookedDice.getNextMove());
	}

	@Override
	public int getPositionOfPlayer(String name) throws PlayerNotFoundException {
		if(!playerPosition.containsKey(name)) {
			throw new PlayerNotFoundException(name);
		}
		return playerPosition.get(name);
	}
	
	// to move currentPlayer pointer to next player
	private void nextPlayer() {
		currentPlayer = (currentPlayer+1)%playerList.size();
	}

	// to move current player to next position
	private void moveCurrentPlayer(int moves) {
		String player = playerList.get(currentPlayer);
		int nextPosition = moves+playerPosition.get(player);
		if(nextPosition<= gameBoardSize ) {
			playerPosition.put(player,nextPosition);
			System.out.println(player+"\t-> Moved to "+nextPosition+" from "+(nextPosition-moves)+" with moves "+moves);
			checkForSnakeOrLadder(nextPosition);
		}
		else {
			System.out.println(player+"\t-> Unable to move ahead from "+(nextPosition-moves)+" with moves "+moves);
		}
		
		//win condition
		if(gameBoardSize == playerPosition.get(playerList.get(currentPlayer))){
			System.out.println(player+"\t-> won the game.");
			stop();
		}
		if(moves==6) {
			System.out.println(player+"\t-> got extra chance, got "+moves);
		}
		else {
			nextPlayer();
		}
	}

	private void checkForSnakeOrLadder(int loopDetection) {
		String player = playerList.get(currentPlayer);
		int position = playerPosition.get(player);
		int isSnakeOrLadder = snakeLadder.getOrDefault(position, -1);
		while(isSnakeOrLadder != -1 && position!=isSnakeOrLadder) {
			playerPosition.put(player, isSnakeOrLadder);
			if(position>isSnakeOrLadder) {
				System.out.println(player+"\t-> Moved to "+isSnakeOrLadder+" from "+position+" due to Snake");
			}
			else {
				System.out.println(player+"\t-> Moved to "+isSnakeOrLadder+" from "+position+" using Ladder");
			}
			position = playerPosition.get(player);
			isSnakeOrLadder = snakeLadder.getOrDefault(position, -1);
			if(position==loopDetection) {
				System.out.println(player+"\t-> got Stuck in loop, found at position "+loopDetection);
				break;
			}
		}		
	}

	@Override
	public boolean isGameOver() {
		return isGameOver;
	}
	
	@Override
	public boolean isGameRunning() {
		return isGameRunning;
	}
}
