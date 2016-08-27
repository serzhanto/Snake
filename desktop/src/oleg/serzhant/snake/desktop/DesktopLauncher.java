package oleg.serzhant.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import oleg.serzhant.snake.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("user.name","seconduser");
		config.resizable = false;
		config.title = "Snake";
		config.width = 900;
		config.height = 600;

		new LwjglApplication(new Game(), config);
	}

}
