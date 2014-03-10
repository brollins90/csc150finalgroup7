package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Sprite {

	protected ArrayList<Image> images;
	protected int imageIndex;
	protected Point position;
	protected int width;

	public Sprite(Point startPosition, ArrayList<Image> images) {

		this.images = images;
		this.setImage(0);
		this.position = startPosition;
		this.width = this.images.get(0).getWidth(null);
	}

	public Image getImage() {
		return this.images.get(this.imageIndex);
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
