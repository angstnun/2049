package ar.edu.Proyecto2048.controller;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import ar.edu.Proyecto2048.model.ReplayModel;
import ar.edu.Proyecto2048.util.Direction;

public class ReplayController {
	
	private static ReplayModel gameplayReplay;
	private static Integer index = 0;
	private static Timer timer = new Timer(500, null);
	private static Boolean replayReady = Boolean.FALSE;
	
	private static void setGameplayReplay(ReplayModel replayModel) {
		ReplayController.gameplayReplay = replayModel;
	}
	
	public static ReplayModel getGameplayReplay() {
		return ReplayController.gameplayReplay;
	}
	
	public static Boolean getReplayReady() {
		return replayReady;
	}

	public static void setReplayReady(Boolean ready) {
		ReplayController.replayReady = ready;
	}

	public static void replayInit(ReplayModel replayModel) {
		ReplayController.setGameplayReplay(replayModel);
	}
	
	public static void setTimer(ActionListener l) {
		ReplayController.getTimer().addActionListener(l);
		ReplayController.ready();
	}
	
	public static Timer getTimer() {
		return ReplayController.timer;
	}
	
	public static void start() {
		if(ReplayController.isReady())
			ReplayController.getTimer().start();
	}
	
	public static void stopCheck() {
		if(index.equals(getGameplayReplay().getMovements().size())){
			ReplayController.stopTimer();
			ReplayController.setIndex(0);
		}
	}
	
	public static void stopTimer(){
		ReplayController.getTimer().stop();
	}
	
	public static void addMovement(Direction direction) {
		ReplayController.getGameplayReplay().getMovements().add(direction);
	}

	public static Integer getIndex() {
		return ReplayController.index;
	}
	
	public static void setIndex(Integer value) {
		ReplayController.index = value;
	}

	public static Boolean isReady() {
		return ReplayController.replayReady;
	}
	
	public static void ready() {
		ReplayController.setReplayReady(Boolean.TRUE);
	}
}