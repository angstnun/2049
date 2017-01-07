package ar.edu.Proyecto2048.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.edu.Proyecto2048.view.BooleanButton;
import ar.edu.Proyecto2048.view.GameplayView;
import ar.edu.Proyecto2048.view.MainMenuView;
import ar.edu.Proyecto2048.view.MainView;

public class EndGameDimmerListener implements ActionListener {
	
	public void actionPerformed(ActionEvent actionEvent) {
		
		if(((BooleanButton)actionEvent.getSource()).getText().equals("Yes")) 
		{
			MainView.getMainLayeredPane().removeDimmer();
			MainView.getMainLayeredPane().swapFrontPanel(new GameplayView("replay"));		
		}
		else 
		{
			MainView.getMainLayeredPane().removeDimmer();
			MainView.getMainLayeredPane().swapFrontPanel(new MainMenuView());			
		}
		
	}
	
}