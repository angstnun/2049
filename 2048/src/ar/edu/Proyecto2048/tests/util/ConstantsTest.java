package ar.edu.Proyecto2048.tests.util;

import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.junit.Test;

import ar.edu.Proyecto2048.util.Constants;

public class ConstantsTest {
	
	public Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/ar/edu/Proyecto2048/config/Config.properties")));
			return properties;
		} catch (IOException e) {
			try {
				properties.load(new FileInputStream(new File("ar/edu/Proyecto2048/config/ConfigDesktop.properties")));
				return properties;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Test
	public void testLoadVars() {
		Constants.loadVars(this.getProperties());
		for(Field field : Constants.class.getDeclaredFields()) 
			assertNotEquals(field, null);
	}

	@Test
	public void testChangeBoardSize() {
		Integer auxBoardSize = Constants.BOARD_SIZE;
		Constants.changeBoardSize(6);
		assertNotEquals((int)auxBoardSize, Constants.BOARD_SIZE);
	}

	@Test
	public void testChangeTileColor() {
		Constants.changeTileColor(2);
		Integer auxTileColor = Constants.TILE_COLOR;
		Constants.changeTileColor(4);
		assertNotEquals(auxTileColor, Constants.TILE_COLOR);
	}

	@Test
	public void testChangeBoardColor() {
		Constants.changeBoardColor(3);
		Integer auxBoardColor = Constants.BOARD_COLOR;
		Constants.changeBoardColor(5);
		assertNotEquals(auxBoardColor, Constants.BOARD_COLOR);
	}

}