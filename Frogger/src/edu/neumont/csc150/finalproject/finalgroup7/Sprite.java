package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A basic object that can be painted on the screen
 * 
 * @author Blake Rollins & Wyatt Reynolds
 */

//CSC150 Identified Requirement – 4.1 Two classes inherit
/*
*  Enemy Friendly and Frog all inherit from Sprite
*/
public abstract class Sprite {

	//CSC150 Identified Requirement – 1.5 Foregone setters
	/*
	*  The imageKeys are only set during construction, nothing outside of the class can change the images
	*/
	protected ArrayList<String> imageKeys;
	protected Point position;
	protected int imageIndex;
	protected int width;

	//CSC150 Identified Requirement – 1.5 Foregone setters
	/*
	*  The friendly setting can only get set inside the class (or and inherited class) this protects the
	*  information and disallows the calling code from changing the game in ways that it shouldn't change  
	*/
	protected boolean friendly;

	/**
	 * A basic object that can be painted on the screen
	 * @param startPosition	The position the the image will be painted
	 * @param imageKeys	The list of image that the Sprite may use
	 * @param width	The width of the Sprite
	 */
	public Sprite(Point startPosition, ArrayList<String> imageKeys, int width) {

		this.imageKeys = imageKeys;
		this.setImage(0);
		this.position = startPosition;
		this.width = width;
		this.friendly = true;
	}

	/**
	 * Checks if two Sprites overlap
	 * @param otherSprite	The Sprite to check the collision
	 * @return	If the Sprites collide
	 */
	public boolean checkCollision(Sprite otherSprite) {

		// Check that both Sprites are in the same row (In this game, the Sprites cannot collide if they don't have the same Y value
		if (otherSprite.position.y == this.position.y) {
			if (this.getLeftExtreme() < otherSprite.getRightExtreme() && this.getRightExtreme() > otherSprite.getLeftExtreme()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the key to the imageMap of the current image
	 * @return The current imageKey
	 */
	public String getImageKey() {
		return this.imageKeys.get(this.imageIndex);
	}

	/**
	 * The farthest left point on the Sprite
	 * @return	The farthest left point on the Sprite
	 */
	private int getLeftExtreme() {
		return position.x;
	}

	/**
	 * Returns a copy of the current position
	 * @return	A copy of the current position
	 */
	public Point getPosition() {
		return new Point(this.position.x, this.position.y);
	}

	/**
	 * The farthest right point on the Sprite
	 * @return	The farthest right point on the Sprite
	 */
	private int getRightExtreme() {
		return position.x + width;
	}

	public int getWidth() {
		return this.width;
	}
	
	public boolean isFriendly() {
		return this.friendly;
	}

	/**
	 * Updates the Sprite's position based on the current location and the speed
	 */
	public abstract void move();

	/**
	 * Changes the current image the the image at the specified index
	 * @param index	The index of the image to use for the Sprite
	 */
	public void setImage(int index) {
		this.imageIndex = index;
	}

}
