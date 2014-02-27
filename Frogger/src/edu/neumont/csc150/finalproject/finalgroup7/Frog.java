package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Frog extends Sprite {
	private Image[] imgArray;

<<<<<<< .mine
=======
	private Image imageUp;
	private Image imageLeft;
	private Image imageRight;
	private Image imageDown;

>>>>>>> .r18
<<<<<<< .mine
	public Frog(Point startPosition, boolean movesToTheLeft, int spriteSpeed, String imagePath) {
		super(startPosition, movesToTheLeft, spriteSpeed, imagePath);
		
		imgArray = new Image[4];
		imgArray[0] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving left.png");
		imgArray[1] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving up.png");
		imgArray[2] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving right.png");
		imgArray[3] = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "moving down.png");
=======
	public Frog(Point startPosition, int spriteSpeed, String imagePath) {
		super(startPosition, spriteSpeed, imagePath);
		this.imageUp = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
>>>>>>> .r18
	}
<<<<<<< .mine
	
	public void setImage(int imageNum){
		this.image = imgArray[imageNum];
	}
	
	
=======
	
	public void addAltImages(String left, String right, String down) {
		this.imageLeft = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + left);
		this.imageRight = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + right);
		this.imageDown = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + down);
	}

	public void setImageUp() {
		super.image = this.imageUp;
	}

	public void setImageDown() {
		super.image = this.imageDown;
	}

	public void setImageLeft() {
		super.image = this.imageLeft;
	}

	public void setImageRight() {
		super.image = this.imageRight;
	}
>>>>>>> .r18
}