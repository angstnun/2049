package ar.edu.Proyecto2048.tests.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.controller.ReplayController;
import ar.edu.Proyecto2048.model.BoardModel;
import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.model.TileModel;
import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.PlayerState;
import ar.edu.Proyecto2048.util.Position;
import ar.edu.Proyecto2048.util.Randomizer;
import ar.edu.Proyecto2048.util.ReplayActionListener;

public class GameplayControllerTest {

	@Test
	public void testSpawnTileAt() {
		GameplayController.MAIN_BOARD = new BoardModel();
		GameplayController.MAIN_BOARD.getBoard()[2][2] = new TileModel(8, new Position(2,2));
		GameplayController.spawnTileAt(2, 2);
		assertFalse(GameplayController.MAIN_BOARD.getBoard()[2][2].getValue().equals(2));
	}

	@Test
	public void testBoardMover() {
		Integer counter = 0;
		GameplayController.debug = Boolean.TRUE;
		GameplayController.play();
		GameplayController.boardMover(Direction.RIGHT);
		for(int i=0;i<Constants.BOARD_SIZE;i++) 
			if(GameplayController.MAIN_BOARD.getBoard()[i][4] != null)
				counter++;
		assertEquals(counter, (Integer)2);
		
	}

	@Test
	public void testPlay() {
		Integer counter = 0;
		GameplayController.play();
		assertTrue((Integer)Randomizer.nextRandomInt() instanceof Integer);
		for(int i=0;i<Constants.BOARD_SIZE;i++)
			for(int j=0;j<Constants.BOARD_SIZE;j++)
				if(GameplayController.MAIN_BOARD.getBoard()[i][j] != null)
					counter++;
		assertTrue(counter.equals(2));
		assertTrue(ReplayController.getGameplayReplay() instanceof ReplayModel);
		assertTrue(GameplayController.currentPlayer.getId().equals(0));
	}

	@Test
	public void testSwitchPlayer() {
		Constants.PLAYERS_NUMBER = 3;
		GameplayController.play();
		Integer auxPlayer = GameplayController.currentPlayer.getId();
		GameplayController.switchPlayer();
		assertNotEquals(auxPlayer, GameplayController.currentPlayer.getId());
	}

	@Test
	public void testCheckGameplayState() {
		GameplayController.play();
		GameplayController.MAIN_BOARD.setLoser(Boolean.TRUE);
		assertEquals(GameplayController.checkGameplayState(), PlayerState.LOSER); 
	}

	@Test
	public void testReplay() {
		ReplayController.setTimer(new ReplayActionListener());
		ReplayController.addMovement(Direction.UP);
		GameplayController.replay();
		assertEquals(ReplayController.getGameplayReplay().getMovements().size(), 1);
		assertEquals(ReplayController.getIndex(), (Integer)0);
	}

}
