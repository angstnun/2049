package ar.edu.Proyecto2048.tests.model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.model.BoardModel;
import ar.edu.Proyecto2048.model.TileModel;
import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Randomizer;

public class BoardModelTest {

	@Test
	public void testBoardModel() {
		for(Field field : BoardModel.class.getDeclaredFields()) 
			assertNotEquals(field, null);
	}

	@Test
	public void testIsLoser() {
		BoardModel testBoardModel = new BoardModel();
		testBoardModel.setLoser(Boolean.TRUE);
		assertTrue(testBoardModel.isLoser() instanceof Boolean);
		assertTrue(testBoardModel.isLoser());
	}

	@Test
	public void testSetLoser() {
		BoardModel testBoardModel = new BoardModel();
		testBoardModel.setLoser(Boolean.TRUE);
		assertTrue(testBoardModel.isLoser());
	}

	@Test
	public void testIsWinner() {
		BoardModel testBoardModel = new BoardModel();
		testBoardModel.setWinner(Boolean.TRUE);
		assertTrue(testBoardModel.isWinner() instanceof Boolean);
		assertTrue(testBoardModel.isWinner());
	}

	@Test
	public void testSetWinner() {
		BoardModel testBoardModel = new BoardModel();
		testBoardModel.setWinner(Boolean.TRUE);
		assertTrue(testBoardModel.isWinner());
	}

	@Test
	public void testGetBoard() {
		BoardModel testBoardModel = new BoardModel();
		assertTrue(!(testBoardModel.getBoard().equals(null)));
		assertTrue(testBoardModel.getBoard() instanceof TileModel[][]);
	}

	@Test
	public void testSetBoard() {
		BoardModel testBoardModel = new BoardModel();
		testBoardModel.setBoard(new TileModel[3][3]);
		assertTrue(!(testBoardModel.getBoard().equals(null)));
		assertTrue(testBoardModel.getBoard().equals(new TileModel[3][3]));
	}

	@Test
	public void testIsBoardFull() {
		Integer i = 0;
		GameplayController.MAIN_BOARD = new BoardModel();
		Randomizer.initRandomizer();
		while(!GameplayController.MAIN_BOARD.isBoardFull()) {
			GameplayController.spawnTile();
			i++;
		}
		assertTrue(i.equals(Constants.BOARD_SIZE * Constants.BOARD_SIZE));
	}

}