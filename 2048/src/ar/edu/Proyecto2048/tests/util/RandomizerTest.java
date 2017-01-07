package ar.edu.Proyecto2048.tests.util;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ar.edu.Proyecto2048.model.Seed;
import ar.edu.Proyecto2048.util.Randomizer;

public class RandomizerTest {
	
	@Test
	public void testInitRandomizer() {
		Randomizer.initRandomizer();
		assertNotEquals(Randomizer.getSeed(), null);
		assertNotEquals(Randomizer.getRandomizer(), null);
	}

	@Test
	public void testInitRandomizerSeed() {
		Seed testSeed = new Seed();
		Randomizer.initRandomizer(testSeed);
		assertEquals(testSeed, Randomizer.getSeed());
	}

	@Test
	public void testGetSeed() {
		Randomizer.initRandomizer();
		Seed aux = Randomizer.getSeed();
		assertTrue(aux instanceof Seed);
		Randomizer.initRandomizer();
		assertNotEquals(aux, Randomizer.getSeed());
	}
	
	@Test
	public void testGetRandomizer() {
		Randomizer.initRandomizer();
		assertTrue(Randomizer.getRandomizer() instanceof Random);
	}

	@Test
	public void testNextRandomInt() {
		Randomizer.initRandomizer();
		Integer[] testIntegerArray1 = new Integer[20];
		Integer[] testIntegerArray2 = new Integer[20];
		for (int i = 0; i < testIntegerArray1.length; i++) {
			testIntegerArray1[i] = Randomizer.nextRandomInt();
			testIntegerArray2[i] = Randomizer.nextRandomInt();
			
		}
		assertNotEquals(testIntegerArray1, testIntegerArray2);
		
	}
	
}