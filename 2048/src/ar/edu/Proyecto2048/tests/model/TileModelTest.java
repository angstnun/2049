package ar.edu.Proyecto2048.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.model.TileModel;
import ar.edu.Proyecto2048.util.Position;

public class TileModelTest {

	@Test
	public void testTileModelPosition() {
		TileModel testTile1 = new TileModel(3, new Position(3, 4));
		assertEquals(testTile1.getValue().intValue(), 3);
	}

	@Test
	public void testGetPosition() {
		TileModel testTile1 = new TileModel(new Position(8, 9));
		assertTrue(testTile1.getPosition().equals(new Position(8, 9)));
	}

	@Test
	public void testSetPosition() {
		TileModel testTile1 = new TileModel(new Position(8, 9));
		testTile1.setPosition(new Position(2, 2));
		assertTrue(testTile1.getPosition().equals(new Position(2, 2)));
	}

	@Test
	public void testGetValue() {
		TileModel testTile1 = new TileModel(3, new Position(3, 4));
		assertTrue(testTile1.getValue() == 3);
	}

	@Test
	public void testSetValue() {
		TileModel testTile1 = new TileModel(3, new Position(3, 4));
		testTile1.setValue(8);
		assertTrue(testTile1.getValue() == 8);
	}

	@Test
	public void testAdd() {
		TileModel testTile1 = new TileModel(3, new Position(3, 4));
		TileModel testTile2 = new TileModel(3, new Position(3, 4));
		testTile1.add(testTile2);
		assertTrue(testTile1.getValue() == 6);
	}

	@Test
	public void testEqualsObject() {
		TileModel testTile1 = new TileModel(new Position(3, 4));
		TileModel testTile2 = new TileModel(new Position(3, 4));
		assertEquals(testTile1, testTile2);
	}

	@Test
	public void testIs2048() {
		TileModel testTile1 = new TileModel(2048, new Position(3, 4));
		assertTrue(testTile1.is2048());
	}

}
