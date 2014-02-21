package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public abstract class Sprite {
	
	public Point position;
	private boolean isMovingLeft;
	private int speed;
	public int width;
	public Image image;
	
	public Sprite(Point startPosition, boolean movesToTheLeft, int spriteSpeed, String imagePath) {
		
		this.position = startPosition;
		this.isMovingLeft = movesToTheLeft;
		this.speed = spriteSpeed;
		this.image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
		this.width = image.getWidth(null);		
	}
	
	private int getLeftExtreme() {
		return position.x;
	}
	
	private int getRightExtreme() {
		return position.x + width;
	}
	
	public boolean checkCollision(Sprite otherSprite) {
		
		if (otherSprite.getLeftExtreme() < this.getRightExtreme()) {
			return true;
		}
		if (otherSprite.getRightExtreme() > this.getLeftExtreme()) {
			return true;
		}
		return false;
	}
	
	

}
