package ar.edu.Proyecto2048.tests.model;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.controller.ReplayController;
import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.util.Direction;

public class ReplayModelTest {

	@Test
	public void testReplayModel() {
		ReplayModel replayModelTest = new ReplayModel();
		assertNotEquals(replayModelTest, null);
	}

	@Test
	public void testGetMovements() {
		ReplayController.replayInit(new ReplayModel());
		ReplayController.addMovement(Direction.UP);
		assertEquals(ReplayController.getGameplayReplay().getMovements().size(), 1);
	}

}