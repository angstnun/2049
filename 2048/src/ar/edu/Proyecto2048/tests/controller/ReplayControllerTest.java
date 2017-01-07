package ar.edu.Proyecto2048.tests.controller;

import static org.junit.Assert.*;

import javax.swing.Timer;

import org.junit.Test;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.controller.ReplayController;
import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.ReplayActionListener;

/**Class ReplayControllerTest
 * The method testReplayInit was ommited due to its implementation similarities to
 * the testGetGameplayReplay method(). (They're basically getter/setter).
 * @author Angelo
 */

public class ReplayControllerTest {

	@Test
	public void testGetGameplayReplay() {
		ReplayController.replayInit(new ReplayModel());
		assertTrue(ReplayController.getGameplayReplay()!= null);
		assertTrue(ReplayController.getGameplayReplay() instanceof ReplayModel);
	}

	@Test
	public void testSetTimer() {
		ReplayController.setTimer(new ReplayActionListener());
		assertTrue(ReplayController.isReady());
		assertTrue(ReplayController.getTimer().getActionListeners()[0] instanceof ReplayActionListener);
	}
	
	@Test
	public void testGetTimer() {
		assertTrue(ReplayController.getTimer() instanceof Timer);
	}

	@Test
	public void testStart() {
		ReplayController.getTimer().stop();
		ReplayController.setReplayReady(Boolean.FALSE);
		ReplayController.replayInit(new ReplayModel());
		ReplayController.addMovement(Direction.RIGHT);
		GameplayController.debug = Boolean.TRUE;
		//If the timer isn't set, the timer won't start.
		GameplayController.replay();
		assertFalse(ReplayController.isReady());
	}

	@Test
	public void testStopCheck() {
		ReplayController.getTimer().stop();
		ReplayController.setReplayReady(Boolean.FALSE);
		ReplayController.replayInit(new ReplayModel());
		ReplayController.addMovement(Direction.RIGHT);
		ReplayController.setTimer(new ReplayActionListener());
		GameplayController.debug = Boolean.TRUE;
		GameplayController.replay();
		Boolean flag = Boolean.TRUE;
		assertTrue(ReplayController.getTimer().isRunning());
		//Wait until the timer does its thing.
		while (flag) {
	            try {
					Thread.sleep(2 * 1000);
					flag = Boolean.FALSE;
				} 
	            catch (InterruptedException e) {
					e.printStackTrace();
	            }
		}
		//And it should be stopped by now (Each loop takes 700 milliseconds.
		assertFalse(ReplayController.getTimer().isRunning());
	}

	@Test
	public void testStopTimer() {
		ReplayController.ready();
		ReplayController.start();
		assertTrue(ReplayController.getTimer().isRunning());
		ReplayController.stopTimer();
		assertFalse(ReplayController.getTimer().isRunning());
	}

	@Test
	public void testAddMovement() {
		ReplayController.replayInit(new ReplayModel());
		ReplayController.addMovement(Direction.DOWN);
		assertTrue(ReplayController.getGameplayReplay().getMovements().get(0).equals(Direction.DOWN));
	}

	@Test
	public void testGetIndex() {
		assertTrue(ReplayController.getIndex() != null);
		assertTrue(ReplayController.getIndex() instanceof Integer);
	}

	@Test
	public void testSetIndex() {
		ReplayController.setIndex(3);
		assertTrue(ReplayController.getIndex().equals(3));
	}

	@Test
	public void testisReady() {
		assertTrue(ReplayController.isReady() instanceof Boolean);
	}

	@Test
	public void testReady() {
		ReplayController.ready();
		assertEquals(ReplayController.isReady(), Boolean.TRUE);
	}

}
