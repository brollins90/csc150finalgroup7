package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * An Enemy is a sprite that is not friendly
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */
public class Enemy extends Sprite {
	protected int speed;
	//CSC150 Identified Requirement – 1.4 Foregone getters
	/*
	*  The changeTimes only have to do with the internal data of the Enemies, so it is not exposed outside of the class
	*  Outside of the Enemy class, all that is seen is what the current image is.
	*/

	protected int changeTimeDefault;
	protected int changeTime;

	/**
	 * An Enemy is a Sprite that requires a speed and a changeTime
	 * 
	 * @param startPosition
	 *            A point for the Sprite to start at
	 * @param imageKeys
	 *            An ArrayList of strings the have keys in the global imageMap
	 * @param width
	 *            The width of the Sprite for collision detection
	 * @param spriteSpeed
	 *            The speed at which the Enemy moves
	 * @param changeTime
	 *            The amount of time to wait before rotating the images
	 */
	public Enemy(Point startPosition, ArrayList<String> imageKeys, int width, int spriteSpeed, int changeTime) {
		//CSC150 Identified Requirement – 4.2 Calling super()
		/*
		*  All the classes that inherit from Sprite call the super constructor because they all need the data that is 
		*  created in that constructor
		*/
		super(startPosition, imageKeys, width);
		this.speed = spriteSpeed;

		//CSC150 Identified Requirement – 4.3 Calling super. anything
		/*
		*  All the classes that inherit form Enemy or Sprite use the super.something syntax
		*  to change their parent's data.
		*/
		super.friendly = false;
		this.changeTimeDefault = changeTime;
		this.changeTime = changeTime;

	}

	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Updates the Sprite's position based on the current location and the speed
	 */

	//CSC150 Identified Requirement – 4.4 Method Overriding
	/*
	*  The Sprite class declares the move method abstract, so most of the classes
	*  in this program override the move() method
	*/
	@Override
	public void move() {
		this.position = new Point(this.position.x + speed, this.position.y);
		// If the Sprite is moving left
		if (this.speed < 0) {
			// check if the whole thing is off the left side of the map
			if (this.position.x < (this.width * -1)) {
				this.position.x = 350;
			}
		} // Moving to the right
		else {
			if (this.position.x > 350 + this.width) { // The this.width is not required, but makes the flow better
				this.position.x = 0 - this.width;
			}
		}
		
		// rotate the images if there are more than one
		int numberOfImages = super.imageKeys.size();
		if (numberOfImages != 1) {
			for (int i = 0; i < numberOfImages; i++) {
				if (this.changeTime == this.changeTimeDefault - i) {
					super.setImage(i);
				}
			}

			// Increase, or reset the timer
			if (this.changeTime < this.changeTimeDefault) {
				this.changeTime++;
			} else if (this.changeTime == this.changeTimeDefault) {
				this.changeTime = 0;
			}

		} // don't change the image if there is only one
		else {			
		}
	}

}
