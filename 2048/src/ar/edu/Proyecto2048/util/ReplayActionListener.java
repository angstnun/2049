package ar.edu.Proyecto2048.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.edu.Proyecto2048.controller.GameplayController;
import ar.edu.Proyecto2048.controller.ReplayController;

public class ReplayActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		GameplayController.boardMover(ReplayController.getGameplayReplay().getMovements().get(ReplayController.getIndex()));
		ReplayController.setIndex(ReplayController.getIndex()+1);
		ReplayController.stopCheck();
	}
	
}
