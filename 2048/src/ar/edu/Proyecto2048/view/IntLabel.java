package ar.edu.Proyecto2048.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Direction;
import ar.edu.Proyecto2048.util.Resources;

public class IntLabel extends JLabel {
	
	private static final long serialVersionUID = 542746011746861450L;
	private Integer value;

	public IntLabel() {
		super(((Integer)Constants.BOARD_SIZE).toString(), SwingConstants.CENTER);
		this.setValue(Constants.BOARD_SIZE);
		this.setFont(Resources.FONT);
		this.setForeground(Constants.FONT_COLOR);
		this.setPreferredSize (new Dimension (64, 64));
		this.setMinimumSize (new Dimension (64, 64));
		this.setMaximumSize (new Dimension (64, 64));
		this.setOpaque(Boolean.FALSE);
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
	}
	
	public Integer getValue() {
		return value;
	}

	private void setValue(Integer value) {
		this.value = value;
	}

	public void incrementInt(Integer minLimit){
		if(getValue() < minLimit){
			this.setValue(getValue()+1);
			this.setText(getValue().toString());
			this.repaint();
		}	
	}
	
	public void decrementInt(Integer maxLimit){
		if(getValue() > maxLimit){
			this.setValue(getValue()-1);
			this.setText(getValue().toString());
			this.repaint();
		}
	}
	
	public void changeValue(Direction direction, Integer maxLimit, Integer minLimit) {
		if(direction.equals(Direction.RIGHT))this.incrementInt(maxLimit);
		else this.decrementInt(minLimit);		
	}
	
}