package ar.edu.Proyecto2048.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.Resources;

public class DimmerView extends JPanel{
	
	private static final long serialVersionUID = 1546123674245038168L;
	private Integer condition;
	private InputMap inputMap;
	private ActionMap actionMap;
	private ButtonsLabel buttonsLabel;	
	private Component buttonsPanel;
	private Integer currentOptionSelected;
	
	public DimmerView(String tipoDimmer, ActionListener buttonsListener){
		String buttonsLabel = null;
		switch(tipoDimmer){
		case "options confirm":
			buttonsLabel = "Save new config";
			this.setButtonsPanel(new YesNoButtonsPanel(buttonsListener));
			break;
		case "exit confirm":
			buttonsLabel = "Exit now";
			this.setButtonsPanel(new YesNoButtonsPanel(buttonsListener));
			break;
		case "winner":
			buttonsLabel = "<html><body>your a winrar<br>Watch replay?</body></html>";
			this.setButtonsPanel(new YesNoButtonsPanel(buttonsListener));
			break;
		case "loser":
			buttonsLabel = "<html><body>SUCKS TO SUCK<br>Watch replay?</body></html>";
			this.setButtonsPanel(new YesNoButtonsPanel(buttonsListener));
			break;
		case "players selection":
			buttonsLabel = "Number of players";
			this.setButtonsPanel(new IntLabel());
			break;
		}
		this.setButtonsLabel(new ButtonsLabel(buttonsLabel));
		this.setCurrentOptionSelected(0);
		this.initDimmerAesthetics();
		this.setKeyBindings();
	}
	
	private ButtonsLabel getButtonsLabel() {
		return buttonsLabel;
	}

	private void setButtonsLabel(ButtonsLabel buttonsLabel2) {
		this.buttonsLabel = buttonsLabel2;
	}

	private Component getButtonsPanel() {
		return buttonsPanel;
	}

	private void setButtonsPanel(Component buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}
	
	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getCurrentOptionSelected() {
		return currentOptionSelected;
	}

	public void setCurrentOptionSelected(Integer currentOptionSelected) {
		this.currentOptionSelected = currentOptionSelected;
	}
	
	private void initDimmerAesthetics() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, 800, 600);
		this.add(Box.createVerticalGlue());
		this.add(this.getButtonsLabel());
		this.add(Box.createVerticalGlue());
		this.add(this.getButtonsPanel());
		if(this.getButtonsPanel() instanceof JLabel)
		{
			((JLabel)this.getButtonsPanel()).setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		else
		{
			((JPanel)this.getButtonsPanel()).setAlignmentX(Component.CENTER_ALIGNMENT);					
		}
		this.add(Box.createVerticalGlue());
		this.setBackground(Constants.DIMMER_BG);
		if(this.getButtonsPanel() instanceof YesNoButtonsPanel) {
			((YesNoButtonsPanel)this.getButtonsPanel()).getDimmerOptions()[getCurrentOptionSelected()].requestFocus();
			((YesNoButtonsPanel)this.getButtonsPanel()).getDimmerOptions()[getCurrentOptionSelected()].setBorderPainted(Boolean.TRUE);			
		}
	}

	private void setKeyBindings() {
		this.condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		this.inputMap = getInputMap(condition);
	    this.actionMap = getActionMap();
	    if(this.getButtonsPanel() instanceof YesNoButtonsPanel) {
			this.initializeMenuActionMap(((YesNoButtonsPanel)this.getButtonsPanel()).getDimmerOptions());			
		}
	    else this.IntLabelActionMap();
	}
	
	private void initializeMenuActionMap(JButton[] componentList){
		
		if(componentList != null) {
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Direction.LEFT);
		    actionMap.put(Direction.LEFT, new AbstractAction() {

				private static final long serialVersionUID = -6421907011268058271L;

				public void actionPerformed(ActionEvent arg0) {
					if (getCurrentOptionSelected() > 0){
						componentList[getCurrentOptionSelected()].setBorderPainted(Boolean.FALSE);
						setCurrentOptionSelected(getCurrentOptionSelected()-1);
						componentList[getCurrentOptionSelected()].requestFocusInWindow();
						componentList[getCurrentOptionSelected()].setBorderPainted(Boolean.TRUE);
					}	
		    	}
		    });
		    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Direction.RIGHT);
		    actionMap.put(Direction.RIGHT, new AbstractAction() {
		    	
				private static final long serialVersionUID = 7391136834071002357L;

				public void actionPerformed(ActionEvent arg0) {
		    		if (getCurrentOptionSelected() < componentList.length - 1){
		    			componentList[getCurrentOptionSelected()].setBorderPainted(Boolean.FALSE);
		    			setCurrentOptionSelected(getCurrentOptionSelected()+1);
		    			componentList[getCurrentOptionSelected()].requestFocusInWindow();
		    			componentList[getCurrentOptionSelected()].setBorderPainted(Boolean.TRUE);
		    		}
		    	}
		    });

		}
		
	}
	
	private void IntLabelActionMap(){
		
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Direction.LEFT);
			actionMap.put(Direction.LEFT, new AbstractAction() {

				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					((IntLabel)getButtonsPanel()).changeValue(Direction.LEFT, 10, 2);
				}
				
			});
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Direction.RIGHT);
			actionMap.put(Direction.RIGHT, new AbstractAction() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					((IntLabel)getButtonsPanel()).changeValue(Direction.RIGHT, 10 , 2);
				}
				
			});
			inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
			actionMap.put("ENTER", new AbstractAction() {
				
				private static final long serialVersionUID = 1L;
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Constants.PLAYERS_NUMBER = ((IntLabel)getButtonsPanel()).getValue();
					MainView.getMainLayeredPane().removeDimmer();
					MainView.getMainLayeredPane().swapFrontPanel(new GameplayView("new game"));
				}
				
			});
	}
	
	private class ButtonsLabel extends JLabel {
		
		private static final long serialVersionUID = 5407263965743774432L;

		private ButtonsLabel(String labelText) {
			super("", SwingConstants.CENTER);
			this.setText(labelText);
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (600, 70));
			this.setMinimumSize (new Dimension (600, 70));
			this.setMaximumSize (new Dimension (600, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
		}
		
	}
	
	private class YesNoButtonsPanel extends JPanel{

		private static final long serialVersionUID = 682976115950597594L;
		private JButton[] dimmerOptions;

		public YesNoButtonsPanel(ActionListener buttonsListener) {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			this.setOpaque(Boolean.FALSE);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.initDimmerOptions(buttonsListener);
			this.generateButtons();
		}
		

		private void initDimmerOptions(ActionListener buttonsListener){
			if(buttonsListener != null) {
				this.setDimmerOptions(new JButton[2]);
				this.getDimmerOptions()[0] = new BooleanButton(Boolean.TRUE, buttonsListener);
				this.getDimmerOptions()[1] = new BooleanButton(Boolean.FALSE, buttonsListener);				
			}
		}

		private void generateButtons() {
			this.add(Box.createHorizontalGlue());
			this.add(this.getDimmerOptions()[0]);
			this.add(Box.createHorizontalGlue());
			this.add(this.getDimmerOptions()[1]);
			this.add(Box.createHorizontalGlue());
		}
		
		public JButton[] getDimmerOptions() {
			return dimmerOptions;
		}

		private void setDimmerOptions(JButton[] dimmerOptions) {
			this.dimmerOptions = dimmerOptions;
		}
	}
	
}