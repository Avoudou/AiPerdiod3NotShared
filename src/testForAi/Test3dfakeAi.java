package testForAi;

import java.util.Scanner;

import Visuals.Golf3D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Test3dfakeAi {


	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.forceExit = false;
		config.width = 800;
		config.height = 700;
		Golf3D game = new Golf3D();
		LwjglApplication window = new LwjglApplication(game, config);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			if (scanner.hasNext()) {
				game.moveBall();
			}
		}

	}
}