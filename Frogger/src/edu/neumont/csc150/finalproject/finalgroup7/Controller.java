package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller {
	
	LoaderPanel lPanel;
	GamePanel gPanel;

	public static void main(String[] args) {
		
		Controller c = new Controller();
		c.openLoaderPanel();
	}
	
	public void openLoaderPanel() {
		if (this.gPanel != null) {
			System.out.println("blake");
			this.gPanel.getGameFrame().setVisible(false);
		}
		if (this.lPanel != null) {
			lPanel.getLoaderFrame().setVisible(true);
		} else {
			this.lPanel = new LoaderPanel(new ControllerListener());
		}
	}
	
	public void openGamePanel(String levelFileName) {
		
		if (this.lPanel != null) {
			lPanel.getLoaderFrame().setVisible(false);
		}
//		String levelFileName = ((JButton)arg0.getSource()).getText();
		// System.out.println("Flist: " + levelFileName);
		Config currentMap = new Config(levelFileName);
		Game currentGame = new Game(currentMap, new ControllerListener());
		this.gPanel = currentGame.getGamePanel();
		currentGame.play();
	}
	
	private class ControllerListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("command: " + arg0.getActionCommand());
			if (arg0.getActionCommand().equals("OpenLoader")) {
				openLoaderPanel();
			} else {
				openGamePanel(arg0.getActionCommand());
			}	
		}
		
	}
}
