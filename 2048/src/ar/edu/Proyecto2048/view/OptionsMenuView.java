package ar.edu.Proyecto2048.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.Resources;

public class OptionsMenuView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel[] choosers;
	private Map<String, Integer> originalSettings;
	private Integer condition;
	private InputMap inputMap;
	private ActionMap actionMap;
	private Integer currentChooserRow;
	
	public OptionsMenuView ()
	{
		this.setOriginalSettings(new HashMap<String, Integer>());
		this.setChoosers(new JLabel[3]);
		this.setCurrentChooserRow(0);
		this.initChoosers();
		this.initOriginalSettings();
		this.initAestheticConfig();
		this.setKeyBindings();
		
	}
	
	private void initAestheticConfig() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBounds(0, 0, 800, 600);
		this.add(Box.createHorizontalStrut(64*2));
		this.add(new OptionsLabelsPanel());
		this.add(Box.createHorizontalStrut(64*2));
		this.add(new OptionsChoosersPanel());
		this.add(Box.createHorizontalStrut(64*2));
		this.setOpaque(Boolean.FALSE);
		this.getChoosers()[getCurrentChooserRow()].setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.FONT_COLOR));
	}
	
	private void initChoosers() {
		this.getChoosers()[0] = new IntLabel();
		this.getChoosers()[1] = new ColorLabel();
		this.getChoosers()[2] = new BoardColorLabel();
	}
	
	private void initOriginalSettings() {
		this.addIntoOriginalSettings("intLabel", ((IntLabel)getChoosers()[0]).getValue());
		this.addIntoOriginalSettings("colorLabel", ((ColorLabel)getChoosers()[1]).getCurrentColor());
		this.addIntoOriginalSettings("boardColorLabel", ((BoardColorLabel)getChoosers()[2]).getCurrentColor());		
	}
	
	public Map<String, Integer> getOriginalSettings() {
		return originalSettings;
	}

	public void setOriginalSettings(Map<String, Integer> originalSettings) {
		this.originalSettings = originalSettings;
	}
	
	public void addIntoOriginalSettings(String key, Integer val) {
		this.getOriginalSettings().put(key, val);
	}

	private Integer getCurrentChooserRow() {
		return currentChooserRow;
	}

	private void setCurrentChooserRow(Integer currentChooserRow) {
		this.currentChooserRow = currentChooserRow;
	}

	private JLabel[] getChoosers() {
		return choosers;
	}

	private void setChoosers(JLabel[] choosers) {
		this.choosers = choosers;
	}
	
	private Integer getChooserCurrentSetting(Integer i) {
		if(i == 0) return ((IntLabel)getChoosers()[i]).getValue();
		else if(i == 1) return ((ColorLabel)getChoosers()[i]).getCurrentColor();
		else return ((BoardColorLabel)getChoosers()[i]).getCurrentColor();
	}
	
	private void setKeyBindings() {
		this.condition = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		this.inputMap = getInputMap(condition);
	    this.actionMap = getActionMap();
	    initializeMenuActionMap();
	}
	
	private boolean areOriginalSettingsChanged(){
		String[] labels = new String[3];
		labels[0] = "intLabel";
		labels[1] = "colorLabel";
		labels[2] = "boardColorLabel";
		for(int i=0;i<3;i++) 
			if(getOriginalSettings().get(labels[i]) != getChooserCurrentSetting(i)) 
				return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private void initializeMenuActionMap(){
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), Direction.UP);
	    actionMap.put(Direction.UP, new AbstractAction() {
	    	
			private static final long serialVersionUID = 1675767541639063579L;

			public void actionPerformed(ActionEvent arg0) {
				if (currentChooserRow > 0){
					choosers[currentChooserRow].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	
					currentChooserRow -= 1;
                    choosers[currentChooserRow].requestFocusInWindow();
                    choosers[currentChooserRow].setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.FONT_COLOR));
				}
		     }
		  });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), Direction.DOWN);
	    actionMap.put(Direction.DOWN, new AbstractAction() {

			private static final long serialVersionUID = -6193194948080612160L;

			public void actionPerformed(ActionEvent arg0) {
				if (currentChooserRow < choosers.length - 1){
					choosers[currentChooserRow].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	
					currentChooserRow += 1;
                    choosers[currentChooserRow].requestFocusInWindow();
                    choosers[currentChooserRow].setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.FONT_COLOR));
				}
	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), Direction.LEFT);
	    actionMap.put(Direction.LEFT, new AbstractAction() {

			private static final long serialVersionUID = -6421907011268058271L;

			public void actionPerformed(ActionEvent arg0) {
				ChooserActionSwitch(getCurrentChooserRow(), Direction.LEFT);
				
	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), Direction.RIGHT);
	    actionMap.put(Direction.RIGHT, new AbstractAction() {

			private static final long serialVersionUID = -6479495729574569907L;

			public void actionPerformed(ActionEvent arg0) {
				ChooserActionSwitch(getCurrentChooserRow(), Direction.RIGHT);
	    	}
	    });
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
	    actionMap.put("closeWindow", new AbstractAction() {
	    	
	    	private static final long serialVersionUID = -6479495729574569907L;
	    	
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(areOriginalSettingsChanged()){
	    			Constants.changeBoardSize(getChooserCurrentSetting(0));
	    			Constants.changeTileColor(getChooserCurrentSetting(1));
	    			Constants.changeBoardColor(getChooserCurrentSetting(2));
	    		}
	    		MainView.getMainLayeredPane().swapFrontPanel(new MainMenuView());
	    	}
	    });
	}
	
	private void ChooserActionSwitch(Integer currentChooser, Direction direction) {
		switch(currentChooser) {
		case 0:
			if(direction.equals(Direction.RIGHT))((IntLabel) getChoosers()[currentChooser]).incrementInt(Constants.MAXIMUM_BOARD_SIZE);
			else ((IntLabel) getChoosers()[currentChooser]).decrementInt(Constants.MINIMUM_BOARD_SIZE);
			break;
		case 1:
			if(direction.equals(Direction.RIGHT))((ColorLabel) getChoosers()[currentChooser]).nextColor();
			else ((ColorLabel) getChoosers()[currentChooser]).previousColor();
			break;
		case 2:
			if(direction.equals(Direction.RIGHT))((BoardColorLabel) getChoosers()[currentChooser]).nextColor();
			else ((BoardColorLabel) getChoosers()[currentChooser]).previousColor();
			break;
		default:
			System.out.println("Invalid chooser");
		}
	}
	
	private class OptionsChoosersPanel extends JPanel{

		private static final long serialVersionUID = 682976115950597594L;

		private OptionsChoosersPanel() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.generateChoosers();
			this.setOpaque(Boolean.FALSE);
			this.setAlignmentY(Component.CENTER_ALIGNMENT);
		}

		private void generateChoosers() {
			this.add(Box.createVerticalGlue());
			this.add(getChoosers()[0]);
			this.add(Box.createVerticalGlue());
			this.add(getChoosers()[1]);
			this.add(Box.createVerticalGlue());
			this.add(getChoosers()[2]);
			this.add(Box.createVerticalGlue());
		}
	}
	
	private class ColorLabel extends JLabel {
		
		private static final long serialVersionUID = 542746011746861450L;
		private Integer currentColor;
		
		private ColorLabel() {
			super();
			this.setCurrentColor(Constants.TILE_COLOR);
			this.setPreferredSize (new Dimension (64, 64));
			this.setMinimumSize (new Dimension (64, 64));
			this.setMaximumSize (new Dimension (64, 64));
			this.setBackground(Constants.BOARD_COLORS[currentColor]);
			this.setAlignmentY(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.TRUE);
		}
		
		private Integer getCurrentColor() {
			return currentColor;
		}

		private void setCurrentColor(Integer currentColor) {
			this.currentColor = currentColor;
		}

		private void nextColor(){
			if (getCurrentColor() < Constants.BOARD_COLORS.length - 1){
				this.setCurrentColor(getCurrentColor()+1);
				this.setBackground(Constants.BOARD_COLORS[getCurrentColor()]);
				this.repaint();
			}
		}
		
		private void previousColor(){
			if (getCurrentColor() > 0){
				this.setCurrentColor(getCurrentColor()-1);
				this.setBackground(Constants.BOARD_COLORS[getCurrentColor()]);
				this.repaint();
			}
		}
		
	}
	
	private class BoardColorLabel extends JLabel {
		
		private static final long serialVersionUID = 542746011746861450L;
		private Integer currentColor;
		
		private BoardColorLabel() {
			super();
			this.setCurrentColor(Constants.BOARD_COLOR);
			this.setPreferredSize (new Dimension (64, 64));
			this.setMinimumSize (new Dimension (64, 64));
			this.setMaximumSize (new Dimension (64, 64));
			this.setBackground(Constants.BOARD_COLORS[getCurrentColor()]);
			this.setAlignmentY(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.TRUE);
		}

		private Integer getCurrentColor() {
			return currentColor;
		}

		private void setCurrentColor(Integer currentColor) {
			this.currentColor = currentColor;
		}
		
		private void nextColor(){
			if (getCurrentColor() < Constants.BOARD_COLORS.length - 1){
				this.setCurrentColor(getCurrentColor()+1);
				this.setBackground(Constants.BOARD_COLORS[getCurrentColor()]);
				this.repaint();
			}
		}
		
		private void previousColor(){
			if (getCurrentColor() > 0){
				this.setCurrentColor(getCurrentColor()-1);
				this.setBackground(Constants.BOARD_COLORS[getCurrentColor()]);
				this.repaint();
			}
		}
		
	}
	
	private class OptionsLabelsPanel extends JPanel{

		private static final long serialVersionUID = 7859019437498452144L;

		private OptionsLabelsPanel() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setAlignmentY(Component.CENTER_ALIGNMENT);
			this.generateOptions();
			this.setOpaque(Boolean.FALSE);
		}
		
		private void generateOptions() {
			this.add(Box.createVerticalGlue());
			this.add(new BoardSizeLabel());
			this.add(Box.createVerticalGlue());
			this.add(new TileColorLabel());
			this.add(Box.createVerticalGlue());
			this.add(new BackgroundColorLabel());
			this.add(Box.createVerticalGlue());
		}
		
	}
	
	private class TileColorLabel extends JLabel {
		
		private static final long serialVersionUID = -6200031388425658065L;

		private TileColorLabel() {
			super("Tile Color");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setOpaque(Boolean.FALSE);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
	}
	
	private class BoardSizeLabel extends JLabel {
		
		private static final long serialVersionUID = 542746011746861450L;

		private BoardSizeLabel() {
			super("Board Size");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setOpaque(Boolean.FALSE);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
	}
	
	private class BackgroundColorLabel extends JLabel {
		
		private static final long serialVersionUID = 2019069692828310360L;

		private BackgroundColorLabel() {
			super("Board BG Color");
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (80, 70));
			this.setOpaque(Boolean.FALSE);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
	}
	
}