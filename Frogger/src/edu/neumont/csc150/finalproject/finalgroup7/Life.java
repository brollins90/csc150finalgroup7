package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A Life is a Sprite that show how many lives the Frog has left
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Life extends Friendly {

	boolean alive;
	
	/**
	 * The Life Sprite needs a position, an ArrayList of imageKeys and the width of the Sprite
	 * @param startPosition	The position to paint the Sprite
	 * @param images	The Images that will be painted
	 * @param width	The width of the image
	 */
	public Life(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
		this.alive = true;		
	}
	
	/**
	 * If the life is still usable
	 * @return If the life has not been used
	 */
	public boolean isAlive() {
		return this.alive;
	}
	
	/**
	 * Changes the Life's status to used and changes the image
	 */
	public void kill() {
		super.setImage(1);
		this.alive = false;
	}

}
