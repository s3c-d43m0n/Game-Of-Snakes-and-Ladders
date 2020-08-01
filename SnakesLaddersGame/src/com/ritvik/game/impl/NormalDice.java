package com.ritvik.game.impl;

import java.util.Random;

import com.ritvik.game.layout.Dice;

public class NormalDice implements Dice {

	Random r;
	
	public NormalDice() {
		r = new Random();
	}
	
	@Override
	public int getNextMove() {
		return 1+r.nextInt(6);
	}

}
