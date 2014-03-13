package edu.neumont.csc150.finalproject.finalgroup7;

public class Controller {

	public static void main(String[] args) {

		// Open the Map Loader Panel
		Config currentMap = new Config("lvl02.xml");

		// Start main frogger game with selected map
		Game currentGame = new Game(currentMap);
		currentGame.play();

	}
}
