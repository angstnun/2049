package ar.edu.Proyecto2048.util;

import java.util.Random;

import ar.edu.Proyecto2048.model.Seed;

public class Randomizer {
	
	private static Seed seed;
	private static Random randomizer;
	
	public static void initRandomizer() {
		Randomizer.setSeed(new Seed());
		Randomizer.setRandomizer(new Random(Randomizer.getSeed().getValue()));
	}
	
	public static void initRandomizer(Seed seed) {
		Randomizer.setSeed(seed);
		Randomizer.setRandomizer(new Random(Randomizer.getSeed().getValue()));
	}
	
	public static Seed getSeed() {
		return Randomizer.seed;
	}

	private static void setSeed(Seed seed) {
		Randomizer.seed = seed;
	}
	
	public static Random getRandomizer() {
		return randomizer;
	}

	private static void setRandomizer(Random randomizer) {
		Randomizer.randomizer = randomizer;
	}

	public static int nextRandomInt(){
	    return Randomizer.getRandomizer().nextInt(Constants.BOARD_SIZE);
	}

}