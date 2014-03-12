package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public class Turtle extends Log {
	
	public Turtle(Point startPosition, ArrayList<String> imageKeys, int width, int spriteSpeed, int changeTime) {
		super(startPosition, imageKeys, width, spriteSpeed, changeTime);
	}
	
	@Override
	public void move() {
		super.move();
		// if the image is set to transparent, then make the rutle evil.
		if (super.getImageKey().equals("Transparent.png")) {
			super.friendly = false;
		} else {
			super.friendly = true;
		}		
	}
}
