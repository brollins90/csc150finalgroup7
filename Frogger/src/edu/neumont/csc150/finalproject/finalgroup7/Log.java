package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * An Sprite that the Frog can ride on top of
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Log extends Enemy {
	
	/**
	 * has the same as parameters as the Enemy class, except sets the Friendly status to true 
	 * @param startPosition	The starting position
	 * @param imageKeys	The images for the Sprite
	 * @param width	The width of the Sprite
	 * @param spriteSpeed	The speed of the Sprite
	 * @param changeTime	The amount of time before the images change
	 */
	public Log(Point startPosition, ArrayList<String> imageKeys, int width, int spriteSpeed, int changeTime) {
		super(startPosition, imageKeys, width, spriteSpeed, changeTime);
		this.friendly = true;
	}

}
