package com.ritvik.game.impl;

import java.util.Random;

import com.ritvik.game.layout.Dice;

public class CrookedDice implements Dice {

	Random r;
	
	public CrookedDice() {
		r = new Random();
	}
	
	@Override
	public int getNextMove() {
		return 2+2*r.nextInt(3);
	}

}
