package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Frog extends Sprite {
	
	public static final int FROG_DOWN = 3;
	public static final int FROG_LEFT = 1;
	public static final int FROG_RIGHT = 2;
	public static final int FROG_UP = 0;
	private Point a;

	public Frog(Point startPosition, ArrayList<Image> images) {
		super(startPosition, images);
		this.a = startPosition;
	}

	public void setPosition(Point newPosition) {
		super.position = newPosition;
	}
	
	public void reset(){
		this.position = a;
	}
	public void move() {
		// doesnt use this move method to auto move
	}

}