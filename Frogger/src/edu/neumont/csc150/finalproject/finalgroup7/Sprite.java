package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Sprite {

	protected ArrayList<String> imageKeys;
	protected Point position;
	protected int imageIndex;
	protected int width;
	protected boolean friendly;

	public Sprite(Point startPosition, ArrayList<String> imageKeys, int width) {

		this.imageKeys = imageKeys;
		this.setImage(0);
		this.position = startPosition;
		this.width = width;
		this.friendly = true;
	}
	
	public boolean isFriendly() {
		return this.friendly;
	}

	public String getImageKey() {
		return this.imageKeys.get(this.imageIndex);
	}

	public void setImage(int index) {
		this.imageIndex = index;
	}

	public Point getPosition() {
		return this.position;
	}

	public int getWidth() {
		return this.width;
	}

	private int getLeftExtreme() {
		return position.x;
	}

	private int getRightExtreme() {
		return position.x + width;
	}

	public boolean checkCollision(Sprite otherSprite) {

		// Check that both Sprites are in the same row
		if (otherSprite.position.y == this.position.y) {
			if (this.getLeftExtreme() < otherSprite.getRightExtreme() && this.getRightExtreme() > otherSprite.getLeftExtreme()) {
				return true;
			}
		}
		return false;
	}

	public abstract void move();

}
