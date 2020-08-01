package com.ritvik.game.driver;

import java.util.Random;

import com.ritvik.game.impl.GameImpl;
import com.ritvik.game.layout.Game;

public class PlayGame {

	public static void main(String[] args) {
		try {
			Game game = new GameImpl(100);
			
			game.addPlayer("User1");
			game.addPlayer("User2");
			game.addPlayer("User3");

			game.addSnake(99, 10);
			game.addSnake(96, 25);
			game.addSnake(80, 20);
			game.addSnake(32, 5); // loop
			game.addSnake(75, 32); //loop
			game.addSnake(56, 24);
			game.addSnake(93, 15);
			game.addSnake(95, 15);
			
			game.addLadder(7, 77);
			game.addLadder(5, 65); // loop
			game.addLadder(65, 75); //loop
			game.addLadder(9, 21);
			game.addLadder(55, 95);
			game.addLadder(34, 78);
			
			game.start();
			
			Random r = new Random();
			while(!game.isGameOver()) {
				if(r.nextBoolean())
					game.throwNormalDice();
				else
					game.throwCrookedDice();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}