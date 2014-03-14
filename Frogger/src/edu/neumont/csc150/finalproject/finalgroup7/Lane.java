package edu.neumont.csc150.finalproject.finalgroup7;

import java.util.ArrayList;

/**
 * The Lane holds all the Sprites in a row and can be set to evil
 * TODO: should hold the speed of the contained Sprites
 * @author Blake Rollins & Wyatt Reynolds
 *
 */
public class Lane {

	public ArrayList<Sprite> sprites;
	private boolean isFriendly;

	/**
	 * The Lane contains Sprites and a Friendly status
	 * @param spritesInLane	An ArrayList of Sprites in the Lane
	 * @param isFriendlyLane If the Lane is harmful to the Frog without Sprites on it
	 */
	public Lane(ArrayList<Sprite> spritesInLane, boolean isFriendlyLane) {
		this.sprites = spritesInLane;
		this.isFriendly = isFriendlyLane;
	}

	/**
	 * Adds a Sprite to the Lane
	 * @param s	The new Sprite
	 */
	public void addSprite(Sprite s) {
		sprites.add(s);
	}

	/**
	 * If the Lane is Friendly or not
	 * @return If the Lane will harm the Frog
	 */
	public boolean isFriendly() {
		return this.isFriendly;
	}

}
