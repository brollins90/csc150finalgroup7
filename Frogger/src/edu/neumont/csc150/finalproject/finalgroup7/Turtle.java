package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Turtle extends Enemy {
	protected Image goodImage;
	protected Image badImage;
	private int sinkTimer;
	
	public Turtle(Point startPosition, int spriteSpeed, String imagePath, String imagePathBad) {
		super(startPosition, spriteSpeed, imagePath);
		this.goodImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
		this.badImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePathBad);
		this.sinkTimer = 0;
	}
	
	@Override
	public void move() {
		super.move();		
		if(sinkTimer<25){
			sinkTimer++;
		}
		if(sinkTimer == 25){
			super.image = this.badImage;
			super.isEvil = true;
		}
	}
	

}
