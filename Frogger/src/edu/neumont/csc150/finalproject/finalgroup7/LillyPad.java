package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A LillyPad is a Sprite that is the goal location for the Frog
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class LillyPad extends Friendly {

	/**
	 * The status for the LillyPad
	 * @author Blake Rollins & Wyatt Reynolds
	 *
	 */
	public enum LillypadCompleted {
		NOT_COMPLETED, // = 0
		COMPLETED // = 1
	}

	/**
	 * The LillyPad needs the ordinary Sprite parameters
	 * @param startPosition	The Location to paint the Sprite
	 * @param images	The images for the LillyPad
	 * @param width	The width of the image
	 */
	public LillyPad(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
	}

}
