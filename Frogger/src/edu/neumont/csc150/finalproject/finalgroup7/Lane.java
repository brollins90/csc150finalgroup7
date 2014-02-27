package edu.neumont.csc150.finalproject.finalgroup7;

import java.util.ArrayList;

public class Lane {
	
	public ArrayList<Sprite> sprites;
	private boolean isEvil;
	
	public Lane(ArrayList<Sprite> spritesInLane, boolean isEvilLane) {
		this.sprites = spritesInLane;
		this.isEvil = isEvilLane;
	}

	public void addSprite(Sprite s) {
		sprites.add(s);
	}

}
