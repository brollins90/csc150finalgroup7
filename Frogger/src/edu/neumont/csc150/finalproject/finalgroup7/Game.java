package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game {

	private Config map;
	private GamePanel panel;
	private Frog frog;
	private ArrayList<Sprite> sprites;
	private int timerNumber = 100;
	private int lives = 3;

	public Game(Config map) {
		// Load the map
		loadMap(map);

		// Create the GamePanel
		this.panel = new GamePanel(map.getBackgroundImageKey(), new GameKeyListener(), map.getImageMap(), this.sprites, this.frog);

		TimerListener tListener = new TimerListener();
		Timer gameTimer = new Timer(this.timerNumber, tListener);
		gameTimer.setRepeats(true);
		gameTimer.start();
	}

	private void loadMap(Config map) {
		this.map = map;
		this.sprites = new ArrayList<Sprite>();

		for (Lane l : this.map.getLanes()) {
			for (Sprite s : l.sprites) {
				sprites.add(s);
			}
		}

		this.frog = map.getFrog();
	}

	public void play() {
		updatePanel();
	}

	private void updatePanel() {
		this.panel.repaint();
	}

	private void killFrog() {
		lives--;
		if (lives > 0) {
			frog.reset();
		} else {
			System.exit(0);
		}
	}

	private void checkCollision() {
		if (this.frog.getPosition().x < 0 || this.frog.getPosition().x > 350) {
			killFrog();
		}

		for (Sprite s : sprites) {
			if (this.frog.checkCollision(s)) {
				System.out.println("COLLIDED: " + s.toString());
				// If it is not friendly, then kill the frog.
				if (!s.isFriendly()) {
					killFrog();
				} else {
					// If it is a Log 
					if (s instanceof Log) {
						frog.setSpeed(((Log) s).getSpeed());
						frog.move();
					}
					// If it is a LillyPad
					else if (s instanceof LillyPad) {
						frog.reset();
						s.setImage(LillyPad.lillypad_completed.COMPLETED.ordinal());
					}
				}
			}

		}
	}

	private void moveSprites() {
		for (Sprite s : sprites) {
			s.move();
		}
		updatePanel();
	}

	public void receiveKey(int keyCode) {
		// System.out.println("key was received: " + keyCode);

		switch (keyCode) {
		case 37:
			System.out.println("Left");
			this.frog.move(Frog.frog_direction.FROG_LEFT, this.map.getNumberOfRows(), this.map.getLaneHeight(), this.map.getNumberOfColumns(), this.map.getColumnWidth());
			break;
		case 38:
			System.out.println("Up");
			this.frog.move(Frog.frog_direction.FROG_UP, this.map.getNumberOfRows(), this.map.getLaneHeight(), this.map.getNumberOfColumns(), this.map.getColumnWidth());
			break;
		case 39:
			System.out.println("Right");
			this.frog.move(Frog.frog_direction.FROG_RIGHT, this.map.getNumberOfRows(), this.map.getLaneHeight(), this.map.getNumberOfColumns(), this.map.getColumnWidth());
			break;
		case 40:
			System.out.println("Down");
			this.frog.move(Frog.frog_direction.FROG_DOWN, this.map.getNumberOfRows(), this.map.getLaneHeight(), this.map.getNumberOfColumns(), this.map.getColumnWidth());
			break;
		default:
			System.out.println("Other: " + keyCode);
			break;
		}
		// moveSprites();
	}

	private class GameKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// System.out.println("game-keyPressed: " + arg0.getKeyCode());
			receiveKey(arg0.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// System.out.println("game-keyReleased: " + arg0.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// System.out.println("game-keyTyped: " + arg0.getKeyCode());
		}

	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Move
			moveSprites();
			// Check Collision
			checkCollision();
		}

	}
}
