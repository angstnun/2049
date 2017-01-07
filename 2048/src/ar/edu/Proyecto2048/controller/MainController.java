package ar.edu.Proyecto2048.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ar.edu.Proyecto2048.util.Constants;
import ar.edu.Proyecto2048.util.Resources;
import ar.edu.Proyecto2048.view.MainView;

public class MainController {
	
	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/ar/edu/Proyecto2048/config/Config.properties")));
			Constants.loadVars(properties);
			Resources.load();
		} catch (IOException e) {
			properties.load(new FileInputStream(new File("ar/edu/Proyecto2048/config/ConfigDesktop.properties")));
				Constants.loadVars(properties);
				Resources.load();
		}
		new MainView();
	}
	
}