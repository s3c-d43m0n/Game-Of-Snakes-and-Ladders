package com.ritvik.game.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ritvik.game.impl.CrookedDice;
import com.ritvik.game.impl.GameImpl;
import com.ritvik.game.impl.NormalDice;
import com.ritvik.game.layout.Dice;
import com.ritvik.game.layout.Game;

public class TestCases {

	@Test
	public void normalDiceTest() {
		Dice d = new NormalDice();
		for(int i=0; i<20; i++) {
			int moves = d.getNextMove();
			assertTrue( moves>=1 && moves<=6 );
		}
	}
	
	@Test
	public void crookedDiceTest() {
		Dice d = new CrookedDice();
		for(int i=0; i<20; i++) {
			int moves = d.getNextMove();
			assertTrue( moves%2 == 0 );
		}
	}
	
	@Test
	public void boardSizeTest() {
		Game g =null;
		try{
			g= new GameImpl(0);
		}
		catch(Exception e) {
			
		}
		assertNull(g);
		
		try{
			g= new GameImpl(-100);
		}
		catch(Exception e) {
			
		}
		assertNull(g);
		
		
		try{
			g= new GameImpl(60);
		}
		catch(Exception e) {
			
		}
		assertNotNull(g);
	}

	@Test
	public void snakeTest() {
		Game g =new GameImpl(20);
		
		boolean snake_created = false;
		
		try{
			g.addSnake(50, 2);
			snake_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(snake_created);
		
		try{
			g.addSnake(20, 10);
			snake_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(snake_created);
		
		try{
			g.addSnake(2, 8);
			snake_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(snake_created);
		
		try{
			g.addSnake(16, 5);
			snake_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertTrue(snake_created);
		
	}
	
	@Test
	public void ladderTest() {
		Game g =new GameImpl(20);
		
		boolean ladder_created = false;
		
		try{
			g.addLadder(50, 2);
			ladder_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(ladder_created);
		
		try{
			g.addLadder(10, 20);
			ladder_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(ladder_created);
		
		try{
			g.addLadder(2, 50);
			ladder_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertFalse(ladder_created);
		
		try{
			g.addLadder(5, 16);
			ladder_created=true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertTrue(ladder_created);
		
	}
	
	@Test
	public void zeroPlayerTest() {
		Game g = new GameImpl(5);
		g.start();
		assertFalse(g.isGameRunning());
		g.addPlayer("Test");
		g.start();
		assertTrue(g.isGameRunning());
	}
	
}
