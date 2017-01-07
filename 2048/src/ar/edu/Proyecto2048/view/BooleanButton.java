package ar.edu.Proyecto2048.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Resources;

public class BooleanButton extends JButton{
		
		private static final long serialVersionUID = 5407263965743774432L;
		
		public BooleanButton(Boolean type, ActionListener buttonListener) {
			super();
			this.typeSelector(type);
			this.setFont(Resources.FONT);
			this.setForeground(Constants.FONT_COLOR);
			this.setPreferredSize (new Dimension (100, 70));
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setOpaque(Boolean.FALSE);
			this.setContentAreaFilled(Boolean.FALSE);
			this.addActionListener(buttonListener);
			this.addKeyListener(Constants.ON_ENTER);
			this.setBorderPainted(Boolean.FALSE);
			this.setFocusPainted(Boolean.FALSE);
		}
		
		private void typeSelector(Boolean type) {
			if (type.equals(Boolean.TRUE))
				this.setText("Yes");
			else
				this.setText("No");
		}

}
