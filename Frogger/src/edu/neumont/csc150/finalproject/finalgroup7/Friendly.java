package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * An Friendly is a sprite that is friendly
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */

public class Friendly extends Sprite {

	/**
	 * An Friendly is a Sprite that represents a not evil Sprite
	 * 
	 * @param startPosition
	 *            A point for the Sprite to start at
	 * @param imageKeys
	 *            An ArrayList of strings the have keys in the global imageMap
	 * @param width
	 *            The width of the Sprite for collision detection
	 */
	public Friendly(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
	}

	/**
	 * Updates the Sprite's position based on the current location and the speed
	 */
	@Override
	public void move() {
		// Basic Friendly Sprites don't move
	}

}
