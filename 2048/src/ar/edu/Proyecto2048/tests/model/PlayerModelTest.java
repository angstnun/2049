package ar.edu.Proyecto2048.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.model.PlayerModel;

/**PlayerModelTest JUnit class.
 * Setter tests removed due to being tested inside Getter tests.
 * @author Angelo
 */

public class PlayerModelTest {

	@Test
	public void testPlayerModel() {
		PlayerModel playerModelTest = new PlayerModel(80);
		assertTrue(playerModelTest.getId().equals(80));
		assertTrue(playerModelTest.getnMoves().equals(0));
		assertTrue(playerModelTest.getScore().equals(0));
	}

	@Test
	public void testGetnMoves() {
		PlayerModel playerModelTest = new PlayerModel(2);
		assertTrue(playerModelTest.getnMoves() instanceof Integer);
		playerModelTest.setnMoves(2);
		assertTrue(playerModelTest.getnMoves().equals(2));
	}


	@Test
	public void testGetId() {
		PlayerModel playerModelTest = new PlayerModel(2);
		assertTrue(playerModelTest.getId() instanceof Integer);
		playerModelTest.setId(8);
		assertTrue(playerModelTest.getId().equals(2));
	}


	@Test
	public void testGetScore() {
		PlayerModel playerModelTest = new PlayerModel(2);
		assertTrue(playerModelTest.getScore() instanceof Integer);
		playerModelTest.setScore(666);
		assertTrue(playerModelTest.getScore().equals(666));
	}

}
