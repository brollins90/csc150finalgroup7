package edu.neumont.csc150.finalproject.finalgroup7;

import java.awt.Point;
import java.util.ArrayList;

public class Frog extends Sprite {

	public enum frog_direction {
		FROG_UP, // = 0
		FROG_LEFT, // = 1
		FROG_RIGHT, // = 2
		FROG_DOWN // =3
	}

	private Point startingLocation;
	private int speed;

	public Frog(Point startPosition, ArrayList<String> imageKeys, int width) {
		super(startPosition, imageKeys, width);
		super.friendly = true;
		this.startingLocation = startPosition;
		this.speed = 0;
	}

	public void setPosition(Point newPosition) {
		super.position = newPosition;
	}

	public void setSpeed(int newSpeed) {
		this.speed = newSpeed;
	}

	public void reset() {
		this.position = startingLocation;
	}

	public void move() {
		this.position = new Point(this.position.x + this.speed, this.position.y);
	}

	public void move(frog_direction dir, int numRows, int laneHeight, int numCols, int columnWidth) {

		Point movePoint = new Point(0, 0);

		this.setImage(dir.ordinal());

		switch (dir) {
		case FROG_LEFT:
			movePoint = new Point(-1, 0);
			break;
		case FROG_RIGHT:
			movePoint = new Point(1, 0);
			break;
		case FROG_UP:
			movePoint = new Point(0, -1);
			break;
		case FROG_DOWN:
			movePoint = new Point(0, 1);
			break;
		}

		// Move horizontal
		if (movePoint.x != 0) {
			int newColumn = ((this.getPosition().x + (movePoint.x * columnWidth)) / columnWidth) * columnWidth;
			if (newColumn >= 0 && newColumn < numCols * columnWidth) {
				this.setPosition(new Point(newColumn, this.getPosition().y));
			}
		}
		// Move vertical
		if (movePoint.y != 0) {
			int newRow = this.getPosition().y + (movePoint.y * laneHeight);
			if (newRow >= 1 && newRow < numRows * laneHeight) {
				this.setPosition(new Point(this.getPosition().x, newRow));
			} else if (newRow == 0) {
				int newColumn = ((this.getPosition().x) / columnWidth) * columnWidth;
				this.setPosition(new Point(newColumn, newRow));
			}
		}

	}

}