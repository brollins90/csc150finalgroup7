package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Enemy extends Sprite {
	protected boolean isEvil;
	
	public Enemy(Point startPosition, int spriteSpeed, ArrayList<Image> images, int changeTime) {
		super(startPosition, spriteSpeed, images, changeTime);
		this.isEvil = true;
	}

	public boolean getIsEvil(){
		return this.isEvil;
	}
	
}
