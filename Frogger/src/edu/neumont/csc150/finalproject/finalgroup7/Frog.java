package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * An Frog is a sprite that is controlled by the user
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */

public class Frog extends Sprite {

	/**
	 * Represents the directions the from can move, and what the index of the proper
	 * frog image is in the array of imageKeys
	 */
	public enum FrogDirection {
		FROG_UP, // = 0
		FROG_LEFT, // = 1
		FROG_RIGHT, // = 2
		FROG_DOWN // =3
	}

	private Point startingLocation;
	private int speed;

	/**
	 * The Frog is a user controlled Sprite
	 * 
	 * @param startPosition
	 *            A point for the Sprite to start at
	 * @param imageKeys
	 *            An ArrayList of strings the have keys in the global imageMap
	 * @param width
	 *            The width of the Sprite for collision detection
	 */
	public Frog(Point startPosition, ArrayList<String> imageKeys, int width) {
		super(startPosition, imageKeys, width);
		super.friendly = true;
		this.startingLocation = startPosition;
		this.speed = 0;
	}

	/**
	 * Updates the Sprite's position based on the current location and the speed
	 */
	@Override
	public void move() {
		this.position = new Point(this.position.x + this.speed, this.position.y);
	}

	/**
	 * Updates the Frog's position based on the FrogDirection passed in
	 * @param dir	The direction for the Frog to move
	 * @param numRows	The number of rows on the board
	 * @param laneHeight	The height of a single row
	 * @param numCols	The number of columns on the board
	 * @param columnWidth	The width of a single column
	 */
	public void move(FrogDirection dir, int numRows, int laneHeight, int numCols, int columnWidth) {

		Point movePoint = new Point(0, 0);

		this.setImage(dir.ordinal());

		switch (dir) {
		case FROG_LEFT:
			movePoint = new Point(-1, 0);
			break;
		case FROG_RIGHT:
			movePoint = new Point(1, 0);
			break;
		case FROG_UP:
			movePoint = new Point(0, -1);
			break;
		case FROG_DOWN:
			movePoint = new Point(0, 1);
			break;
		}

		// Move horizontal
		if (movePoint.x != 0) {
			int newColumn = ((this.getPosition().x + (movePoint.x * columnWidth)) / columnWidth) * columnWidth;
			if (newColumn >= 0 && newColumn < numCols * columnWidth) {
				super.position = new Point(newColumn, this.getPosition().y);
			}
		}
		// Move vertical
		if (movePoint.y != 0) {
			int newRow = this.getPosition().y + (movePoint.y * laneHeight);
			if (newRow >= 1 && newRow < numRows * laneHeight) {
				super.position = new Point(this.getPosition().x, newRow);
			} else if (newRow == 0) {
				int newColumn = ((this.getPosition().x) / columnWidth) * columnWidth;
				super.position = new Point(newColumn, newRow);
			}
		}

	}

	/**
	 * Moves the Frog back to it's starting position
	 */
	public void reset() {
		this.position = startingLocation;
	}
	
	/**
	 * Sets the Speed for the Frog to move, when automatically moving, (riding a Log)
	 * @param newSpeed	The new speed
	 */
	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}

}