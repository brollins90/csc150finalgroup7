package edu.neumont.csc150.finalproject.finalgroup7;

import javax.swing.JFrame;


public class Controller {
	
	// Mario is 20x30
	// Square is 25x40
	// 

	public static void main(String[] args) {

//		JFrame frame = new JFrame("Super Frogger");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
		
		// Open the Map Loader Panel
		Config currentMap = new Config("lvl01.xml");
		
//		// Start main frogger game with selected map
		Game currentGame = new Game(currentMap);
//		
//		// Create panel from game
//		// move inside game if possible
//		GamePanel fPanel = new GamePanel(currentGame.getNumberOfColumns(), currentGame.getNumberOfRows());
//		
//		currentGame.addPanel(fPanel);
		currentGame.play();
		
//		frame.setContentPane(new TestPanel(currentMap));
//		
//		frame.pack();
//		frame.setVisible(true);
	}
}
