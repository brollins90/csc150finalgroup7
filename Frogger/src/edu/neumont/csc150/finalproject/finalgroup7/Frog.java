package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Frog extends Sprite {
//	private Image[] imgArray;
//	private Image imageUp;
//	private Image imageLeft;
//	private Image imageRight;
//	private Image imageDown;

	public Frog(Point startPosition, int spriteSpeed, ArrayList<Image> images) {
		super(startPosition, spriteSpeed, images, 0);		
//		imgArray = new Image[4];
//		imgArray[0] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving left.png");
//		imgArray[1] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving up.png");
//		imgArray[2] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving right.png");
//		imgArray[3] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving down.png");
	}
	
//	public void setImage(int imageNum){
//		this.image = super.images.get(imageNum);
//	}

	public void setPosition(Point newPosition) {
		this.position = newPosition;
	}
	
//	public void addAltImages(String left, String right, String down) {
//		this.imageLeft = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + left);
//		this.imageRight = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + right);
//		this.imageDown = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + down);
//	}


}