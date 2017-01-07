package ar.edu.Proyecto2048.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.model.Seed;

public class SeedTest {

	@Test
	public void testSeed() {
		assertNotEquals(new Seed().getValue(), null);
	}

	@Test
	public void testGetValue() {
		Seed testSeed = new Seed();
		Long auxValue = testSeed.getValue();
		assertEquals(auxValue, (Long)testSeed.getValue());
	}

	@Test
	public void testSetValue() {
		Seed testSeed = new Seed();
		Long auxValue = testSeed.getValue(); 
		testSeed.setValue(System.currentTimeMillis()+1);
		assertNotEquals(auxValue, (Long)testSeed.getValue());
	}

}
