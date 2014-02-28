package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;

public class Enemy extends Sprite {
	protected boolean isEvil;
	
	public Enemy(Point startPosition, int spriteSpeed, String imagePath) {
		super(startPosition, spriteSpeed, imagePath);
		this.isEvil = true;
	}

	public boolean getIsEvil(){
		return this.isEvil;
	}
	
}
