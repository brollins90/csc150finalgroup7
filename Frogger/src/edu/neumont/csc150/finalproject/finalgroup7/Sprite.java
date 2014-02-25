package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public abstract class Sprite {

	private Image image;
	private boolean movingLeft;
	private Point position;
	private int speed;
	private int width;
	
	public Sprite(Point startPosition, boolean movesToTheLeft, int spriteSpeed, String imagePath) {

		this.image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
		this.movingLeft = movesToTheLeft;
		this.position = startPosition;
		this.speed = spriteSpeed;
		this.width = image.getWidth(null);		
	}
	
	public Image getimage() {
		return this.image;
	}
	
	public boolean getMovingLeft() {
		return this.movingLeft;
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
		
		if (otherSprite.getLeftExtreme() < this.getRightExtreme()) {
			return true;
		}
		if (otherSprite.getRightExtreme() > this.getLeftExtreme()) {
			return true;
		}
		return false;
	}
	
	

}
