package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game {

	private Map map;
	private GamePanel panel;
	private Frog frog;
	private ArrayList<Sprite> sprites;
	
	
	public Game(Map map) {
		// Load the map
		loadMap(map);
		
		// Create the GamePanel
		this.panel = new GamePanel(map.getBackgroundImage());
		this.panel.addGameListener(new GameKeyListener());

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
		// Print the background
		//panel.printBackground();
		// Print the Bad guys
		this.panel.addSprites(this.sprites);
		// Print the frog
		this.panel.addFrog(this.frog);
		//panel.placeObject(this.frogPosition.x, this.frogPosition.y, Color.GREEN);	
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
	
	public void receiveKey (int keyCode) {
		//System.out.println("key was received: " + keyCode);
		switch (keyCode) {
		case 37:
			System.out.println("Left");
			moveFrog(new Point(-1, 0));
			break;
		case 38:
			System.out.println("Up");
			moveFrog(new Point(0, -1));
			break;
		case 39:
			System.out.println("Right");
			moveFrog(new Point(1, 0));
			break;
		case 40:
			System.out.println("Down");
			moveFrog(new Point(0, 1));
			break;
		default:
			System.out.println("Other: " + keyCode);
			break;
		}
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
}
