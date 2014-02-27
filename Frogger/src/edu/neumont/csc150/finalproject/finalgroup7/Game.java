package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
<<<<<<< .mine
import java.awt.Toolkit;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> .r18
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game {

	private Map map;
	private GamePanel panel;
	private Frog frog;
	private ArrayList<Sprite> sprites;
	private int a = 0;
	private int lives = 3;

	
	public Game(Map map) {
		// Load the map
		loadMap(map);
		
		// Create the GamePanel
		this.panel = new GamePanel(map.getBackgroundImage());
		this.panel.addGameListener(new GameKeyListener());
		this.panel.addSprites(this.sprites);
		this.panel.addFrog(this.frog);
		
		TimerListener tListener = new TimerListener();
		Timer gameTimer = new Timer( 100, tListener);
		gameTimer.setRepeats(true);
		gameTimer.start();

//		// Play
//		updatePanel();
	}
	
	private void loadMap(Map map) {
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
//		// Print the background
//		//panel.printBackground();
//		// Print the Bad guys
//		this.panel.addSprites(this.sprites);
//		// Print the frog
//		this.panel.addFrog(this.frog);
//		//panel.placeObject(this.frogPosition.x, this.frogPosition.y, Color.GREEN);	
		this.panel.repaint();
	}
	
//	public int getNumberOfColumns() {
//		return this.map.numberOfColumns;
//	}
//
//	public int getNumberOfRows() {
//		return this.map.numberOfRows;
//	}
//

//	public void addPanel(GamePanel fPanel) {
//		this.panel = fPanel;
//		fPanel.addGameListener(new GameKeyListener());
//	}
	
	private void moveFrog(Point movePoint) {
		// Move horizontal
		if (movePoint.x != 0) {
			int newColumn = this.frog.getPosition().x + (movePoint.x * this.map.getColumnWidth());
			if (newColumn >= 0 && newColumn < map.getNumberOfColumns() * this.map.getColumnWidth()) {
				this.frog.setPosition(new Point(newColumn, this.frog.getPosition().y));
			}
		}
		// Move vertical
		if (movePoint.y != 0) {
			int newRow = this.frog.getPosition().y + (movePoint.y * this.map.getLaneHeight());
			if (newRow >= 0 && newRow < map.getNumberOfRows() * this.map.getLaneHeight()) {
				this.frog.setPosition(new Point(this.frog.getPosition().x, newRow));
			}
		}
		updatePanel();
	}
	
	private void moveSprites() {
		for (Sprite s : sprites) {
			s.move();
		}
		updatePanel();
	}
	
	public void receiveKey (int keyCode) {
		//System.out.println("key was received: " + keyCode);
		
		switch (keyCode) {
		case 37:
			System.out.println("Left");
			frog.setImage(0);
			moveFrog(new Point(-1, 0));
			break;
		case 38:
			System.out.println("Up");
			moveFrog(new Point(0, -1));
			frog.setImage(1);
			break;
		case 39:
			System.out.println("Right");
			frog.setImage(2);
			moveFrog(new Point(1, 0));
			break;
		case 40:
			System.out.println("Down");
			frog.setImage(3);
			moveFrog(new Point(0, 1));
			break;
		default:
			System.out.println("Other: " + keyCode);
			break;
		}
		//moveSprites();
	}
	
	private class GameKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			//System.out.println("game-keyPressed: " + arg0.getKeyCode());
			receiveKey(arg0.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			//System.out.println("game-keyReleased: " + arg0.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("game-keyTyped: " + arg0.getKeyCode());
		}
		
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			moveSprites();
		}
		
	}
}
