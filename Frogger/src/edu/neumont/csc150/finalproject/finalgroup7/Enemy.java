package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Enemy extends Sprite {
	protected boolean isEvil;
	protected int speed;
	protected int changeTimeDefault;
	protected int changeTime;
	
	public Enemy(Point startPosition, ArrayList<String> imageKeys, int width, int spriteSpeed, int changeTime) {
		super(startPosition, imageKeys, width);
		this.speed = spriteSpeed;
		this.isEvil = true;
		this.changeTimeDefault = changeTime;
		this.changeTime = changeTime;

	}

	public int getSpeed() {
		return this.speed;
	}

	public boolean getIsEvil(){
		return this.isEvil;
	}
	
	@Override
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
		
		int numberOfImages = super.imageKeys.size();
		// rotate the images
		if (numberOfImages != 1) {
			for (int i = 0; i < numberOfImages; i++) {
				if (this.changeTime == this.changeTimeDefault - i) {
					super.setImage(i);
				}
			}

			// Increase, or reset the timer
			if(this.changeTime < this.changeTimeDefault){
				this.changeTime++;
			} else if(this.changeTime == this.changeTimeDefault){
				this.changeTime = 0;
			}
			
		} else {
			// dont change the image
		}
	}
	
}
