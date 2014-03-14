package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * An Sprite that the Frog can ride on top of that can sink and become evil
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Turtle extends Log {

	/**
	 * has the same as parameters as the Enemy class 
	 * @param startPosition	The starting position
	 * @param imageKeys	The images for the Sprite
	 * @param width	The width of the Sprite
	 * @param spriteSpeed	The speed of the Sprite
	 * @param changeTime	The amount of time before the images change
	 */
	public Turtle(Point startPosition, ArrayList<String> imageKeys, int width, int spriteSpeed, int changeTime) {
		super(startPosition, imageKeys, width, spriteSpeed, changeTime);
	}

	/**
	 * Updates the Sprite's position based on the current location and the speed
	 */
	@Override
	public void move() {
		super.move();
		// if the image is set to transparent, then make the Turtle evil.
		if (super.getImageKey().equals("Transparent.png")) {
			super.friendly = false;
		} else {
			super.friendly = true;
		}		
	}
}
