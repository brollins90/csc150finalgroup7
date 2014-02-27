package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Frog extends Sprite {

	private Image imageUp;
	private Image imageLeft;
	private Image imageRight;
	private Image imageDown;

	public Frog(Point startPosition, int spriteSpeed, String imagePath) {
		super(startPosition, spriteSpeed, imagePath);
		this.imageUp = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource(".").getPath() + imagePath);
	}
	
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
}