package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public class Life extends Friendly {

	boolean alive;
	
	public Life(Point startPosition, ArrayList<String> images, int width) {
		super(startPosition, images, width);
		this.alive = true;		
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void kill() {
		super.setImage(1);
		this.alive = false;
	}

}
