
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		final long seed = parseSeed(args);
		printTestRunMetadata(seed);

		Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = seed < 0 ? new Random() : new Random(seed);
	
		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
		
	}

	private static void printTestRunMetadata(final long seed) {
		System.out.println("---");
		System.out.println(String.format("seed: %d", seed));
		System.out.println("---");
	}

	private static long parseSeed(final String[] args) {
		final long seed;
		if (args.length > 0) {
			seed = Long.parseLong(args[0], 10);
		} else {
			seed = -1L;
		}
		return seed;
	}
}
