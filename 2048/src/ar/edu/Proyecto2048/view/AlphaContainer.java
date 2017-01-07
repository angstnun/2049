package ar.edu.Proyecto2048.view;

import java.awt.Graphics;

import javax.swing.JComponent;

public class AlphaContainer extends JComponent{

	private static final long serialVersionUID = -1999173862004039205L;
	private JComponent component;

	public AlphaContainer(JComponent component) {
		this.component = component;
		this.setBounds(0, 0, 800, 600);
		this.setOpaque(Boolean.FALSE);
		component.setOpaque(Boolean.FALSE);
		this.add(component);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor( component.getBackground() );
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
}