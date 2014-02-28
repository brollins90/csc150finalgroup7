package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Turtle extends Enemy {
	protected Image goodImage;
	protected Image badImage;
	protected Image sinkImage;
	protected Image surfaceImage;
	private int sinkTimer;
	
	public Turtle(Point startPosition, int spriteSpeed, String imagePath, String imagePathBad) {
		super(startPosition, spriteSpeed, imagePath);
		this.goodImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
		this.badImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePathBad);
		this.sinkImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "Wiggler mad sinking.png");
		this.surfaceImage = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "Wiggler surfacing.png");
		this.sinkTimer = 0;
	}
	
	@Override
	public void move() {
		super.move();
		sinkTimer++;
		if(sinkTimer==24){
			super.image = this.badImage;
		}
		if(sinkTimer==26){
			super.image = this.sinkImage;
		}
		if(sinkTimer==28){
			super.image = null;
			super.isEvil = true;
		}
		
		if(sinkTimer == 42){
			super.image = this.surfaceImage;
			super.isEvil = false;

		}
		
		if(sinkTimer==44){
			super.image = this.goodImage;
			sinkTimer=0;
		}
	}
	

}
