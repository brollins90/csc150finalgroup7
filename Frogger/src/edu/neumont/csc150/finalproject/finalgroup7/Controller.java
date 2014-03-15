package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class is the starting point for the Frogger application
 * @author Blake Rollins & Wyatt Reynolds
 *
 */
public class Controller {
	
	LoaderPanel lPanel;
	GamePanel gPanel;

	public static void main(String[] args) {
		
		Controller c = new Controller();
		c.openLoaderPanel();
	}
	
	/**
	 * Opens the LoaderPanel so the user can choose a level to play
	 */
	public void openLoaderPanel() {
		// If the GamePanel has been created, then set it to not visible so the 
		// LoaderPanel is displayed
		if (this.gPanel != null) {
			this.gPanel.getGameFrame().setVisible(false);
		}
		// If the LoaderPanel has been created, then set it to visible
		if (this.lPanel != null) {
			lPanel.getLoaderFrame().setVisible(true);
		} // if it has not been created, then create one. 
		else {
			this.lPanel = new LoaderPanel(new ControllerListener());
		}
	}
	
	/**
	 * Opens the GamePanel created with the inputted level name
	 * @param levelFileName	The name of the config xml
	 */
	public void openGamePanel(String levelFileName) {
		
		// Set the LoaderPanel to not visible so we can see the GamePanel
		if (this.lPanel != null) {
			lPanel.getLoaderFrame().setVisible(false);
		}
		try {
			Config levelConfig = new Config(levelFileName);
			Game currentGame = new Game(levelConfig, new ControllerListener());
			
			// Save a reference to the GamePanel so we can switch back to the LoaderPanel after the game 
			this.gPanel = currentGame.getGamePanel();
			currentGame.play();
		} catch (ConfigurationException e) {
			System.out.println(e);
			System.out.println("Failed to load the level file.  Choose a new level.");
			openLoaderPanel();
		}
	}
	
	/**
	 * The ControllerListener listens for the filename from the LoaderPanel to create the game
	 * And listens to the GamePanel for the new game request.
	 */
	private class ControllerListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// System.out.println("ControllerListener command: " + arg0.getActionCommand());
			if (arg0.getActionCommand().equals("OpenLoader")) {
				openLoaderPanel();
			} // If the recieved command is not "OpenLoader" then it is a filename for a new game 
			else {
				openGamePanel(arg0.getActionCommand());
			}	
		}
		
	}
}
