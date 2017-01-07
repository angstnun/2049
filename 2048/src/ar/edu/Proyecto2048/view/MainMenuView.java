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
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.Resources;

public class MainMenuView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Integer condition;
	private InputMap inputMap;
	private ActionMap actionMap;
	private JButton[] buttons;
	private Integer currentButtonRow;
	
	public MainMenuView ()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, 800, 600);
		this.setCurrentButtonRow(0);
		this.generateMenu();
		this.setOpaque(Boolean.FALSE);
		this.setKeyBindings();
	    initializeMenuActionMap();
	    this.getButtons()[0].requestFocus();
	    this.getButtons()[0].setBorderPainted(Boolean.TRUE);
	}
	
	public Integer getCurrentButtonRow() {
		return currentButtonRow;
	}

	public void setCurrentButtonRow(Integer currentButtonRow) {
		this.currentButtonRow = currentButtonRow;
	}

	public JButton[] getButtons() {
		return buttons;
	}

	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}
	
	private void setKeyBindings() {
		this.condition = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		this.inputMap = getInputMap(condition);
	    this.actionMap = getActionMap();
	    initializeMenuActionMap();
	}
	

	private void initializeMenuActionMap(){
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Direction.UP);
	    actionMap.put(Direction.UP, new AbstractAction() {
	    	
			private static final long serialVersionUID = 1675767541639063579L;

			public void actionPerformed(ActionEvent arg0) {
				if (currentButtonRow > 0){
					buttons[currentButtonRow].setBorderPainted(Boolean.FALSE);
					currentButtonRow -= 1;
                    buttons[currentButtonRow].requestFocusInWindow();
                    buttons[currentButtonRow].setBorderPainted(Boolean.TRUE);
				}
		     }
		  });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Direction.DOWN);
	    actionMap.put(Direction.DOWN, new AbstractAction() {

			private static final long serialVersionUID = -6193194948080612160L;

			public void actionPerformed(ActionEvent arg0) {
				if (currentButtonRow < buttons.length - 1){
					buttons[currentButtonRow].setBorderPainted(Boolean.FALSE);
					currentButtonRow += 1;
                    buttons[currentButtonRow].requestFocusInWindow();
                    buttons[currentButtonRow].setBorderPainted(Boolean.TRUE);
				}
	    	}
	    });
	}
	
	private void generateMenu() {
		this.setButtons(new JButton[4]);
		this.getButtons()[0] = new SinglePlayerButton();
		this.getButtons()[1] = new MultiplayerButton();
		this.getButtons()[2] = new OptionsButton();
		this.getButtons()[3] = new ExitButton();
		this.add(Box.createVerticalGlue());
		this.add(this.getButtons()[0]);
		this.add(Box.createVerticalGlue());
		this.add(this.getButtons()[1]);
		this.add(Box.createVerticalGlue());
		this.add(this.getButtons()[2]);
		this.add(Box.createVerticalGlue());
		this.add(this.getButtons()[3]);
		this.add(Box.createVerticalGlue());
	}
	
	private class SinglePlayerButton extends JButton {
		
		private static final long serialVersionUID = 5407263965743774432L;

		private SinglePlayerButton() {
			super("Single Player");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.setContentAreaFilled(Boolean.FALSE);
			this.addActionListener(new MenuListener());
			this.addKeyListener(Constants.ON_ENTER);
			this.setBorderPainted(Boolean.FALSE);
			this.setFocusPainted(Boolean.FALSE);
			this.setRolloverEnabled(false);
		}
		
	}
	
	private class MultiplayerButton extends JButton {
		
		private static final long serialVersionUID = 1762806389636802880L;

		private MultiplayerButton() {
			super("Multi Player");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.setContentAreaFilled(Boolean.FALSE);
			this.addActionListener(new MenuListener());
			this.addKeyListener(Constants.ON_ENTER);
			this.setBorderPainted(Boolean.FALSE);
			this.setFocusPainted(Boolean.FALSE);
			this.setRolloverEnabled(false);
		}
		
	}
	
	private class OptionsButton extends JButton {	

		private static final long serialVersionUID = -8895083880847890603L;

		private OptionsButton() {
			super("Option");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.setContentAreaFilled(Boolean.FALSE);
			this.addActionListener(new MenuListener());
			this.addKeyListener(Constants.ON_ENTER);
			this.setBorderPainted(Boolean.FALSE);
			this.setFocusPainted(Boolean.FALSE);
			this.setRolloverEnabled(false);
		}
		
	}
	
	private class ExitButton extends JButton {		

		private static final long serialVersionUID = -8834074885907554035L;

		private ExitButton() {
			super("Exit");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.setContentAreaFilled(Boolean.FALSE);
			this.addActionListener(new MenuListener());
			this.addKeyListener(Constants.ON_ENTER);
			this.setBorderPainted(Boolean.FALSE);
			this.setFocusPainted(Boolean.FALSE);
			this.setRolloverEnabled(false);
		}
		
	}
	
	private class MenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent actionEvent) {
			
			if (actionEvent.getSource().getClass().equals(SinglePlayerButton.class)) {
				Constants.PLAYERS_NUMBER = 1;
				MainView.getMainLayeredPane().swapFrontPanel(new GameplayView("new game"));
			}
			
			if (actionEvent.getSource().getClass().equals(MultiplayerButton.class)) {
				MainView.getMainLayeredPane().addDimmer(new AlphaContainer(new DimmerView("players selection", null)));
			}
			
			if (actionEvent.getSource().getClass().equals(OptionsButton.class)) {
				MainView.getMainLayeredPane().swapFrontPanel(new OptionsMenuView());
			}
			
			if (actionEvent.getSource().getClass().equals(ExitButton.class)) {
				System.exit(0);
			}
			
		}
		
	}
	
}