package ar.edu.Proyecto2048.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import ar.edu.Proyecto2048.util.Constants;

public class BackgroundView extends JPanel {

	private static final long serialVersionUID = 7103999318960833811L;
	private static final Image BACKGROUND_IMAGE = Toolkit.getDefaultToolkit().getImage(Constants.IMAGE_PATH);
	
	public BackgroundView() {
		this.setBounds(0, 0, 800, 600);
		this.setOpaque(Boolean.TRUE);
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int iw = 96;
        int ih = 96;
        if (iw > 0 && ih > 0) {
            for (int x = 0; x < 800; x += iw) {
                for (int y = -ih; y < 600; y += ih) {
                    g.drawImage(BACKGROUND_IMAGE, x, y, iw, ih, this);
                }
            }
        }
    }
    
}