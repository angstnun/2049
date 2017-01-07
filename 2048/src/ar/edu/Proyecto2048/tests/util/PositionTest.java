package ar.edu.Proyecto2048.tests.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.util.Position;

public class PositionTest {

	@Test
	public void testPosition() {
		Position testPosition = new Position(3,4);
		assertEquals(testPosition.getX().intValue(), 3);
		assertEquals(testPosition.getY().intValue(), 4);
	}

	@Test
	public void testEqualsPosition() {
		Position testPosition = new Position(5,8);
		assertTrue(testPosition.equals(new Position(5,8)));
		assertFalse(testPosition.equals(new Position(23,2)));
	}

	@Test
	public void testToString() {
		Position testPosition = new Position(3,8);
		assertEquals(testPosition.toString(), testPosition.getX()+" "+testPosition.getY());
	}

}