package ar.edu.Proyecto2048.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Resources {
	
	public static Font FONT;
	public static Font THREE_DIGIT_FONT;
	public static Font FOUR_DIGIT_FONT;
	
	public static void load(){
		try {
			FONT = Font.createFont(Font.TRUETYPE_FONT, new File(Constants.FONT_PATH));
			FONT = FONT.deriveFont(Font.BOLD, 20);
			THREE_DIGIT_FONT = FONT.deriveFont(Font.BOLD, 14);
			FOUR_DIGIT_FONT = FONT.deriveFont(Font.BOLD, 12);
		} catch (IOException|FontFormatException e) {
		     System.out.println(e.getMessage());
		}
		
	}
	
}
