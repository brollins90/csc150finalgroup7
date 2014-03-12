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

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 7L;
	private String backgroundImageKey;
	private Map<String, Image> imageMap;
	private KeyListener gameListener;

	ArrayList<Sprite> sprites;
	Frog frog;

	public GamePanel(String backgroundImageKey, KeyListener gameListener, Map<String, Image> imageMap, ArrayList<Sprite> newSprites, Frog newFrog) {

		this.backgroundImageKey = backgroundImageKey;
		this.imageMap = imageMap;
		this.gameListener = gameListener;
		this.sprites = newSprites;
		this.frog = newFrog;

		JFrame frame = new JFrame("Super Frogger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		this.setFocusable(true);
		this.addKeyListener(new PanelKeyListener());

		// TODO set preferredSize
		this.setPreferredSize(new Dimension(350, 600));

		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background
		g.drawImage(this.imageMap.get(backgroundImageKey), 0, 0, null);// ,
																		// getWidth(),
																		// getHeight(),
																		// this);

		// Paint the Enemies
		for (Sprite s : this.sprites) {
			g.drawImage(this.imageMap.get(s.getImageKey()), s.getPosition().x, s.getPosition().y, null);
		}

		// Paint the frog
		g.drawImage(this.imageMap.get(frog.getImageKey()), frog.getPosition().x, frog.getPosition().y, null);
	}

	// public void addGameListener(KeyListener gameListener) {
	// this.gameListener = gameListener;
	// }
	//
	// public void addSprites(ArrayList<Sprite> newSprites) {
	// this.sprites = newSprites;
	// }
	//
	// public void addFrog(Frog newFrog) {
	// this.frog = newFrog;
	// }

	private class PanelKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// System.out.println("panel-keyPressed: " + arg0.getKeyCode());
			gameListener.keyPressed(arg0);
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// System.out.println("panel-keyReleased: " + arg0.getKeyCode());
			gameListener.keyReleased(arg0);
			repaint();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// System.out.println("panel-keyTyped: " + arg0.getKeyCode());
			gameListener.keyTyped(arg0);
		}

	}

}
