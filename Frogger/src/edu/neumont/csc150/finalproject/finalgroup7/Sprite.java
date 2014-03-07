package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public abstract class Sprite {

	protected Image image;
	protected ArrayList<Image> images;
	private boolean movingLeft;
	//private boolean movingLeft;
	protected Point position;
	protected int changeTimeDefault;
	protected int changeTime;
	protected int speed;
	private int width;
	
	public Sprite(Point startPosition, int spriteSpeed, ArrayList<Image> images, int changeTime) {

		//this.image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
		this.images = images;
		setImage(0);
		//this.movingLeft = movesToTheLeft;
		this.position = startPosition;
		this.speed = spriteSpeed;
		this.width = image.getWidth(null);		
		this.changeTimeDefault = changeTime;
		this.changeTime = changeTime;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(int index){
		this.image = images.get(index);
	}
	
//	public boolean getMovingLeft() {
//		return this.movingLeft;
//	}
	
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
	
	public void move() {
		this.position = new Point(this.position.x + speed, this.position.y);
		if(this.position.x < -77){
			this.position.x = 350;
		}
	}
	
	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}
	
	

}
