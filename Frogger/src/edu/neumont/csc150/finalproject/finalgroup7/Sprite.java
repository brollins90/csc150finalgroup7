package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Sprite {

	protected ArrayList<Image> images;
	protected int imageIndex;
	protected Point position;
//	protected Rectangle rectangle;
	protected int changeTimeDefault;
	protected int changeTime;
	protected int speed;
	private int width;

	public Sprite(Point startPosition, int spriteSpeed, ArrayList<Image> images, int changeTime) {

		this.images = images;
		this.setImage(0);
		this.position = startPosition;
		this.speed = spriteSpeed;
		this.width = 7; // image.getWidth(null);
//		this.rectangle = new Rectangle(position.x, position.y, this.width, 40); // Set a generic 1 for the height
		this.changeTimeDefault = changeTime;
		this.changeTime = changeTime;
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

	public int getSpeed() {
		return this.speed;
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
//			if (this.rectanlge.intersects(otherSprite.rectangle)) {
//				return true;
//			}
		}
		return false;
	}

	public void move() {
		this.position = new Point(this.position.x + speed, this.position.y);
		// If the image is moving left
		if (this.speed < 0) {
			// check if the whole thing is off the left side of the map
			if (this.position.x < (this.width * -1)) {
				this.position.x = 350;
			}
		} else { // Moving to the right
			if (this.position.x > 350 + this.width) {
				this.position.x = 0;
			}
		}
//		this.rectangle = new Rectangle(this.position.x, this.position.y, this.width, 40);
	}

}
