package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The GamePanel displays all the info in the Game
 * @author Blake Rollins & Wyatt Reynolds
 *
 */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 7L;
	private String backgroundImageKey;
	private Map<String, Image> imageMap;
	private KeyListener gameListener;
	private JFrame gameFrame;

	ArrayList<Sprite> sprites;
	Frog frog;

	/**
	 * The GamePanel needs a background, a reference to the Game, the Map will all the images, the ArrayList of Sprites and a copy of the Frog
	 * @param backgroundImageKey	The String that is the key for the background image in the imageMap
	 * @param gameListener	The listener reference to the Game class
	 * @param imageMap	The Map full of images
	 * @param newSprites	The Sprites in the Game
	 * @param newFrog	The Frog in the Game
	 */
	public GamePanel(String backgroundImageKey, KeyListener gameListener, Map<String, Image> imageMap, ArrayList<Sprite> newSprites, Frog newFrog) {

		this.backgroundImageKey = backgroundImageKey;
		this.imageMap = imageMap;
		this.gameListener = gameListener;
		this.sprites = newSprites;
		this.frog = newFrog;
		
		this.gameFrame =  new JFrame("Super Frogger");
		this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.gameFrame.setLocationRelativeTo(null);

		this.setFocusable(true);
		this.addKeyListener(new PanelKeyListener());

		// TODO set preferredSize
		this.setPreferredSize(new Dimension(350, 600));

		this.gameFrame.setContentPane(this);
		this.gameFrame.pack();
		this.gameFrame.setVisible(true);
	}
	
	/**
	 * Gets a reference for the Frame that the GamePanel is displayed on (For the controller class to set focus)
	 * @return	A reference to the Frame that the GamePanel lives in
	 */
	public JFrame getGameFrame() {
		return this.gameFrame;
	}

	/**
	 * Pains all the Sprites on the panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background
		g.drawImage(this.imageMap.get(backgroundImageKey), 0, 0, null);

		// Paint the Enemies
		for (Sprite s : this.sprites) {
			g.drawImage(this.imageMap.get(s.getImageKey()), s.getPosition().x, s.getPosition().y, null);
		}

		// Paint the frog
		g.drawImage(this.imageMap.get(frog.getImageKey()), frog.getPosition().x, frog.getPosition().y, null);
	}

	/**
	 * Listens for input from the User
	 * @author Blake Rollins & Wyatt Reynolds
	 *
	 */
	private class PanelKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			gameListener.keyPressed(arg0);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			gameListener.keyReleased(arg0);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			gameListener.keyTyped(arg0);
		}

	}

}
