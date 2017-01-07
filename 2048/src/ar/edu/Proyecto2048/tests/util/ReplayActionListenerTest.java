package ar.edu.Proyecto2048.tests.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.controller.ReplayController;
import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.ReplayActionListener;

public class ReplayActionListenerTest {

	@Test
	public void testActionPerformed() {
		ReplayController.replayInit(new ReplayModel());
		ReplayController.addMovement(Direction.RIGHT);
		ReplayController.addMovement(Direction.LEFT);
		//ReplayActionListener is where the magic is at.
		ReplayController.setTimer(new ReplayActionListener());
		GameplayController.debug = Boolean.TRUE;
		GameplayController.replay();
		Boolean flag = Boolean.TRUE;
		assertTrue(ReplayController.getTimer().isRunning());
		//Wait until the timer increments the index by 1.
		Integer auxIndex = 0;
		while (flag) {
	            try {
					Thread.sleep(900);
					auxIndex = ReplayController.getIndex();
					flag = Boolean.FALSE;
				} 
	            catch (InterruptedException e) {
					e.printStackTrace();
	            }
		}
		assertTrue(auxIndex.equals(1));
	}

}
