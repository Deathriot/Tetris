package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Map.ScoreMap;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Tetris");
		config.setWindowedMode(ScoreMap.sizeX, ScoreMap.sizeY);
		new Lwjgl3Application(new Tetris(), config);

		// D - Движение направо
		// S - Движение вниз
		// A - Движение влево
		// R - Поворот фигуры
	}
}