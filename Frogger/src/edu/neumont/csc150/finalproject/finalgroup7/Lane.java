package edu.neumont.csc150.finalproject.finalgroup7;

import java.util.ArrayList;

public class Lane {

	public ArrayList<Sprite> sprites;
	private boolean isFriendly;

	public Lane(ArrayList<Sprite> spritesInLane, boolean isEvilLane) {
		this.sprites = spritesInLane;
		this.isFriendly = isEvilLane;
	}

	public boolean isFriendly() {
		return this.isFriendly;
	}

	public void addSprite(Sprite s) {
		sprites.add(s);
	}

}
