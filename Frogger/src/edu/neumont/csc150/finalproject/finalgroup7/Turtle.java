package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Turtle extends Enemy {
	
	public Turtle(Point startPosition, int spriteSpeed, ArrayList<Image> images, int changeTime) {
		super(startPosition, spriteSpeed, images, changeTime);
	}
	
	@Override
	public void move() {
		super.move();
//		System.out.println(super.changeTime);
		int numberOfImages = super.images.size();
		// rotate the images
		if (numberOfImages != 1) {
			for (int i = 0; i < numberOfImages; i++) {
				if (super.changeTime == super.changeTimeDefault - i) {
					super.setImage(i);
				}
			}

			// Increase, or reset the timer
			if(super.changeTime < super.changeTimeDefault){
				super.changeTime++;
			} else if(super.changeTime == super.changeTimeDefault){
				super.changeTime = 0;
				//super.setImage(1);
				//super.image = this.badImage;
				//super.isEvil = true;
			}
			
		} else {
			// dont change the image
		}
		
	}
	

}
