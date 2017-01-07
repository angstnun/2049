package ar.edu.Proyecto2048.util;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import java.util.Properties;

public class Constants {
	
	public static Integer PLAYERS_NUMBER = 1;
	public static String APPNAME = "2048";
	public static String IMAGE_PATH;
	public static String FONT_PATH;
	public static Color FONT_COLOR = new Color(0x94F700);
	public static Color DIMMER_BG =  new Color(0,0,0,220); 
	public static int BOARD_SIZE = 5;
	public static int MINIMUM_BOARD_SIZE = 4;
	public static int MAXIMUM_BOARD_SIZE = 7;
	public static Color[] BOARD_COLORS;
	public static Integer BOARD_COLOR; 
	public static Integer TILE_COLOR;
	public static KeyListener ON_ENTER;
	
	private static String getVar(Properties p, String var, String defaultValue) {
		String val;
		
		if ((val = p.getProperty(var)) == null) {
			System.err.println("Variable <" + var + "> indefaultValueinida, usando defaultValueault ("
					+ defaultValue + ")");
			val = defaultValue;
		}
		
		return val;
	}
	
	private static void loadColors() {
		BOARD_COLORS = new Color[8];
		BOARD_COLORS[0] = new Color(0x470047);
		BOARD_COLORS[1] = new Color(0x5F3275);
		BOARD_COLORS[2] = new Color(0xC797C1);
		BOARD_COLORS[3] = new Color(0xFFD4FF);
		BOARD_COLORS[4] = new Color(0xDE00A6);
		BOARD_COLORS[5] = new Color(0xF9E812);
		BOARD_COLORS[6] = new Color(0xEA5207);
		BOARD_COLORS[7] = new Color(0x99220C);
	}
	
	public static void loadVars(Properties p) {
		IMAGE_PATH = getVar(p, "IMAGE_PATH", ".");
		FONT_PATH = getVar(p, "FONT_PATH", ".");
		ON_ENTER = new KeyAdapter() {
		  @Override
		  public void keyTyped(KeyEvent e) {
		     if (e.getKeyChar() == KeyEvent.VK_ENTER) {
		        ((JButton) e.getComponent()).doClick();
		     }
		  }};
		 Constants.loadColors();
		 BOARD_COLOR = 3;
		 TILE_COLOR = 0;
	}
	
	public static void changeBoardSize(Integer size) {
		BOARD_SIZE = size;
	}
	
	public static void changeTileColor(Integer index) {
		TILE_COLOR = index;
	}
	
	public static void changeBoardColor(Integer index) {
		BOARD_COLOR = index;
	}
	
}