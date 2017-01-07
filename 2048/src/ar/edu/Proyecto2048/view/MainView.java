package ar.edu.Proyecto2048.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import ar.edu.Proyecto2048.util.Constants;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 997517825597392705L;
	private static MainLayeredPane mainLayeredPane;
	
	public MainView() {
		super(Constants.APPNAME);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Point hotSpot = new Point(0,0);
	    BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
	    Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");        
	    this.setCursor(invisibleCursor);
		this.setPreferredSize(new Dimension(800,600));
		this.setResizable(Boolean.FALSE);
		this.setMainLayeredPane(new MainLayeredPane());
		this.add(getMainLayeredPane(), BorderLayout.CENTER);
		this.pack();
		this.setVisible(Boolean.TRUE);
	}
	
	public static MainLayeredPane getMainLayeredPane() {
		return mainLayeredPane;
	}

	public void setMainLayeredPane(MainLayeredPane pane) {
		mainLayeredPane = pane;
	}

//	private Dimension getDimensionCentrada() {
//		Dimension tamPan = Toolkit.getDefaultToolkit().getScreenSize();
//		return new Dimension(tamPan.width, tamPan.height);
//	}
	
	public class MainLayeredPane extends JLayeredPane{

		private static final long serialVersionUID = -7373310658279691188L;
		private JPanel frontPanel;

		private MainLayeredPane() {
			this.addBackground();
			this.addFrontPanel(new MainMenuView());
		}
		
		private JPanel getFrontPanel() {
			return frontPanel;
		}

		private void setFrontPanel(JPanel frontPanel) {
			this.frontPanel = frontPanel;
		}

		private void addBackground() {
			this.add(new BackgroundView(), new Integer(0));
		}
		
		public void swapFrontPanel(JPanel panel) {
			this.remove(0);
			this.addFrontPanel(panel);
			if(panel instanceof MainMenuView) ((MainMenuView)getFrontPanel()).getButtons()[0].requestFocus();
			else this.getFrontPanel().requestFocusInWindow();
		}
		
		private void addFrontPanel(JPanel panel) {
			this.setFrontPanel(panel);
			this.add(getFrontPanel(), new Integer(1));
		}
		
		public void addDimmer(Component dimmer) {
			this.add(dimmer, new Integer(2));
			getComponent(0).requestFocus();
		}
		
		public void removeDimmer() {
			this.remove(0);
		}
		
	}
	
}