package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * The Game holds all the data and logic for the Frogger game
 * @author Blake Rollins & Wyatt Reynolds
 *
 */
public class Game {

	private Config config;
	private GamePanel panel;
	private Frog frog;
	private ArrayList<Sprite> sprites;
	private int timerNumber = 100;
	private int lives;
	private int lillyPadsLeft = 5;
	private boolean gameRunning = false;
	private ActionListener controllerListener;

	/**
	 * The Game requires a Config object built from an xml file and a Listener to communicate with the controller class
	 * @param config	The Config object for the current game
	 * @param cListener	A listener for the calling Controller class
	 */
	public Game(Config config, ActionListener cListener) {
		// Load the map
		loadMap(config);
		this.controllerListener = cListener;

		// Create the GamePanel that will display the Game
		this.panel = new GamePanel(config.getBackgroundImageKey(), new GameKeyListener(), config.getImageMap(), this.sprites, this.frog);

	}

	/**
	 * Handles the storage of scored poins
	 * @param currentPad
	 */
	private void addLillyPadPoint(LillyPad currentPad) {
		// When we score a point, move the Frog back to it's start position
		frog.reset();
		// Change the image of the LillyPad to the completed state
		currentPad.setImage(LillyPad.LillypadCompleted.COMPLETED.ordinal());
		lillyPadsLeft--;
		// When all the lillypad's have been changed to the Completed state inform the user of the win
		if (lillyPadsLeft < 1) {
			gameWon();
		}
	}
	
	/**
	 * Checks all the ways that the Frog can die, also updates the Frog location if it is riding a Log
	 */
	private void checkCollision() {

		// Check if something moved the frog off the edge of the world
		if (this.frog.getPosition().x < 0 || this.frog.getPosition().x > 350) {
			killFrog();
		}

		int currentLane = this.frog.position.y / this.config.getLaneHeight();
		boolean laneIsFriendly = this.config.getLanes()[currentLane].isFriendly();

		boolean ridingAFriendly = false;
		for (Sprite s : sprites) {
			if (this.frog.checkCollision(s)) {
				// If there was a collision with a NOT friendly Sprite, kill the Frog
				if (!s.isFriendly()) {
					killFrog();
				} // Else, figure out what to do
				else {
					ridingAFriendly = true;
					// If it is a Log
					if (s instanceof Log) {
						frog.setSpeed(((Log) s).getSpeed());
					}
					// If it is a LillyPad
					else if (s instanceof LillyPad) {
						addLillyPadPoint((LillyPad) s);
					}
					// TODO: add the extra point items
				}
			}
		}
		if (!laneIsFriendly) {
			// If the Lane is not friendly, and the Frog is not riding a Friendly Sprite, then kill it
			if (!ridingAFriendly) {
				killFrog();
			} // else, tell the Frog to move the same as the Sprite that it is riding
			else {
				frog.move();
			}
		} else {
			// The Frog is ok because the lane is friendly

		}
	}

	/**
	 * Inform the user that they lost the Game
	 */
	private void gameLost() {
		System.out.println("Sorry, you lost :(");
		this.gameRunning = false;
	}

	/**
	 * Inform the user that they won the Game
	 */
	private void gameWon() {
		System.out.println("YAY!!! You won.");
		this.gameRunning = false;
	}
	
	/**
	 * Returns the GamePanel, (Used by the Controller class to set and maintain focus)
	 * @return The GamePanel that is displaying the game
	 */
	public GamePanel getGamePanel() {
		return this.panel;
	}

	/**
	 * Remove a life and move the Frog back to it's starting position
	 */
	private void killFrog() {
		lives--;
		
		/**
		 * This is some REALLY hacky code that will remove a life indicating Sprite from the display, 
		 * I didn't have time to create a new SpriteCollection class that would help solve this problem
		 */
		boolean removedSprite = false;
		for (int spriteIndex = this.sprites.size() -1; spriteIndex > 0; spriteIndex--) {
			Sprite currentSprite = this.sprites.get(spriteIndex);
			if (!removedSprite && currentSprite instanceof Life) {
				Life currentLife = (Life)currentSprite;
				if (currentLife.isAlive()) {
					currentLife.kill();
					removedSprite = true;
				}
			}
		}
		// If there are still some lives, reset the Frog
		if (lives > 0) {
			frog.reset();
		} // If we are out of lives, tell the user they lost 
		else {
			gameLost();
		}
	}
	
	/**
	 * Initialize the Game with the objects from the Config class
	 * @param config
	 */
	private void loadMap(Config config) {
		this.config = config;
		this.sprites = new ArrayList<Sprite>();
		this.lives = config.getStartingLives();

		for (Lane l : this.config.getLanes()) {
			for (Sprite s : l.sprites) {
				sprites.add(s);
			}
		}

		this.frog = config.getFrog();
	}

	/**
	 * Tell all the Sprites in the Game to move
	 */
	private void moveSprites() {
		for (Sprite s : sprites) {
			s.move();
		}
		updatePanel();
	}

	/**
	 * Start the game timer and start accepting user input
	 */
	public void play() {
		this.gameRunning = true;

		TimerListener tListener = new TimerListener();
		Timer gameTimer = new Timer(this.timerNumber, tListener);
		gameTimer.setRepeats(true);
		gameTimer.start();
	}

	/**
	 * Process a request from the user
	 * @param keyCode	The inputed command from the user
	 */
	public void receiveKey(int keyCode) {
		System.out.println("key was received: " + keyCode);

		switch (keyCode) {
		case 10: // 10 is Enter
			if (!gameRunning) {
				this.controllerListener.actionPerformed(new ActionEvent(this, 1, "OpenLoader"));
			}
			
			break;
		case 37: // 37 is Left
			if (gameRunning) {
				this.frog.move(Frog.FrogDirection.FROG_LEFT, this.config.getNumberOfRows(), this.config.getLaneHeight(), this.config.getNumberOfColumns(), this.config.getColumnWidth());
			}
			break;
		case 38: // 38 is Up
			if (gameRunning) {
				this.frog.move(Frog.FrogDirection.FROG_UP, this.config.getNumberOfRows(), this.config.getLaneHeight(), this.config.getNumberOfColumns(), this.config.getColumnWidth());
			}
			break;
		case 39: // 39 is Right
			if (gameRunning) {
				this.frog.move(Frog.FrogDirection.FROG_RIGHT, this.config.getNumberOfRows(), this.config.getLaneHeight(), this.config.getNumberOfColumns(), this.config.getColumnWidth());
			}
			break;
		case 40: // 40 is Down
			if (gameRunning) {
				this.frog.move(Frog.FrogDirection.FROG_DOWN, this.config.getNumberOfRows(), this.config.getLaneHeight(), this.config.getNumberOfColumns(), this.config.getColumnWidth());
			}
			break;
		default: // If it is anything else, ignore it
			break;
		}
	}

	/**
	 * Tell the GamePanel to queue a repaint request
	 */
	private void updatePanel() {
		this.panel.repaint();
	}

	/**
	 * A class that is passed to the GamePanel for it to communicate with the Game
	 * @author Blake Rollins & Wyatt Reynolds
	 *
	 */
	private class GameKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			receiveKey(arg0.getKeyCode());			
		}

		@Override
		public void keyReleased(KeyEvent arg0) { }

		@Override
		public void keyTyped(KeyEvent arg0) { }
	}

	/**
	 * A class that listens for the swing timer for update requests
	 * @author Blake Rollins & Wyatt Reynolds
	 *
	 */
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Only update the GamePanel and move the Sprites if the game is still running
			if (gameRunning) {
				// Move all the Sprites
				moveSprites();
				// Check Collisions
				checkCollision();
			} // We dont need to change anything since the game is no longer running 
			else {
				// TODO:
				// We may need to actually stop the timer here, but since we are 
				// killing the game soon anyway, it is probably OK
			}
		}

	}
}
